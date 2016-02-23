package com.arual.jstock.analyzer.concurrency;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.arual.jstock.analyzer.exception.AnalyzerRuntimeException;
import com.arual.jstock.analyzer.model.Rule;
import com.arual.jstock.analyzer.model.RuleSet;

public class SymbolsProcessorExecutionPool<T> {

	private int threadsNumber;

	private RuleSet<Rule> ruleSet;
	
	private String uuid;

	public SymbolsProcessorExecutionPool() {
		super();
	}

	public SymbolsProcessorExecutionPool(SymbolsProcessorExecutionPoolBuilder<T> builder) {
		this.ruleSet = builder.ruleSet;
		this.threadsNumber = builder.threadsNumber;
		this.uuid = builder.uuid;
	}

	public void processSymbols(Set<String> symbols, LocalDate startDate, LocalDate dateToCheck,
			Class<T> dataGathererClass) {

		ExecutorService symbolProcessorExecutor = Executors.newFixedThreadPool(this.threadsNumber);
		long startMillis = System.currentTimeMillis();

		Iterator<String> symbolsIterator = symbols.iterator();
		while (symbolsIterator.hasNext()) {
			String symbol = symbolsIterator.next();
			SymbolProcessorRunnable<T> symbolProcessorRunnable = new SymbolProcessorRunnable.SymbolProcessorRunnableBuilder<T>()
					.ofSymbol(symbol).startCollectingDate(startDate).toCheckDate(dateToCheck).usingDataGathererClass(dataGathererClass)
					.usingRuleSet(this.ruleSet).uuid(uuid).build();
			symbolProcessorExecutor.execute(symbolProcessorRunnable);
		}
		closeExecutor(symbolProcessorExecutor);
		long endMillis = System.currentTimeMillis();
		System.out.println("Symbols collecting time: " + ((endMillis - startMillis) / 1000) + " seconds for "+symbols.size()+" symbols");
	}

	private void closeExecutor(ExecutorService symbolProcessorExecutor) {
		symbolProcessorExecutor.shutdown(); // Cierro el Executor
		try {
			symbolProcessorExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			throw new AnalyzerRuntimeException("Error closing executor:" + e);
		}
	}

	public static class SymbolsProcessorExecutionPoolBuilder<T> {
		private int threadsNumber = 1;
		private RuleSet<Rule> ruleSet;
		private String uuid;

		public SymbolsProcessorExecutionPoolBuilder<T> usingThreads(int threads) {
			this.threadsNumber = threads;
			return this;
		}

		public SymbolsProcessorExecutionPoolBuilder<T> usingRules(RuleSet<Rule> rules) {
			this.ruleSet = rules;
			return this;
		}
		
		public SymbolsProcessorExecutionPoolBuilder<T> uuid(String uuid) {
			this.uuid = uuid;
			return this;
		}

		public SymbolsProcessorExecutionPool<T> build() {
			return new SymbolsProcessorExecutionPool<T>(this);
		}
	}

}
