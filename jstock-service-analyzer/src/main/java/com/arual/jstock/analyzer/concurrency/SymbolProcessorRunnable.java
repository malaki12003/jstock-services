package com.arual.jstock.analyzer.concurrency;

import java.time.LocalDate;

import com.arual.jstock.analyzer.SymbolAnalyzer;
import com.arual.jstock.analyzer.exception.AnalyzerRuntimeException;
import com.arual.jstock.analyzer.model.Rule;
import com.arual.jstock.analyzer.model.RuleResult;
import com.arual.jstock.analyzer.model.RuleSet;
import com.arual.jstock.analyzer.results.BasicResultHolder;
import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.model.Annotations;

public class SymbolProcessorRunnable<T> implements Runnable {

	private String symbol;
	private LocalDate startDate;
	private LocalDate dateToCheck;
	private Class<T> dataGathererClass;
	private RuleSet<Rule> ruleSet;
	private String uuid;

	public SymbolProcessorRunnable() {
	}

	public SymbolProcessorRunnable(SymbolProcessorRunnableBuilder<T> builder) {
		this.symbol = builder.symbol;
		this.startDate = builder.startDate;
		this.dateToCheck = builder.dateToCheck;
		this.dataGathererClass = builder.dataGathererClass;
		this.ruleSet = builder.ruleSet;
		this.uuid = builder.uuid;
	}

	@Override
	public void run() {
		try {
			DataGatherer dataGatherer = (DataGatherer) this.dataGathererClass.newInstance();
			Annotations annotations = dataGatherer.obtainFinancialDataDaily(symbol, this.startDate, this.dateToCheck);
			
			SymbolAnalyzer symbolAnalyzer = new SymbolAnalyzer();
			RuleSet<RuleResult> result = symbolAnalyzer.analyze(annotations, this.ruleSet, this.dateToCheck);
			BasicResultHolder.insertResult(uuid, symbol, result);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new AnalyzerRuntimeException("Error analyzing symbol " + this.symbol + " :" + e);
		}

	}

	public static class SymbolProcessorRunnableBuilder<T> {
		String symbol;
		LocalDate startDate;
		LocalDate dateToCheck;
		Class<T> dataGathererClass;
		RuleSet<Rule> ruleSet;
		String uuid;

		SymbolProcessorRunnableBuilder<T> ofSymbol(String symbol) {
			this.symbol = symbol;
			return this;
		}

		SymbolProcessorRunnableBuilder<T> startCollectingDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}

		SymbolProcessorRunnableBuilder<T> toCheckDate(LocalDate date2check) {
			this.dateToCheck = date2check;
			return this;
		}

		SymbolProcessorRunnableBuilder<T> usingDataGathererClass(Class<T> dataGathererClass) {
			this.dataGathererClass = dataGathererClass;
			return this;
		}

		SymbolProcessorRunnableBuilder<T> usingRuleSet(RuleSet<Rule> ruleSet) {
			this.ruleSet = ruleSet;
			return this;
		}
		
		SymbolProcessorRunnableBuilder<T> uuid(String uuid) {
			this.uuid = uuid;
			return this;
		}

		public SymbolProcessorRunnable<T> build() {
			return new SymbolProcessorRunnable<T>(this);
		}
	}

}
