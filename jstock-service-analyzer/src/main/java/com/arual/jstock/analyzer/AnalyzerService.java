package com.arual.jstock.analyzer;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.arual.jstock.analyzer.concurrency.SymbolsProcessorExecutionPool;
import com.arual.jstock.analyzer.model.AnalisysResult;
import com.arual.jstock.analyzer.model.Rules;
import com.arual.jstock.analyzer.results.BasicResultHolder;
import com.arual.jstock.collector.impl.YahooDataGatherer;

/**
 * @author Pablo
 * 
 */

public class AnalyzerService {

	private LocalDate collectingStartLocalDate;

	private Set<String> symbolsToAnalyze;

	public AnalyzerService(LocalDate collectingStartLocalDate, Set<String> symbolsToAnalyze) {
		super();
		this.collectingStartLocalDate = collectingStartLocalDate;
		this.symbolsToAnalyze = symbolsToAnalyze;
	}

	public AnalisysResult checkStrictBuyConditionsForStocks(LocalDate date2check) {
		String uuid = UUID.randomUUID().toString();

		SymbolsProcessorExecutionPool<YahooDataGatherer> processorPool = new SymbolsProcessorExecutionPool.SymbolsProcessorExecutionPoolBuilder<YahooDataGatherer>()
				.usingRules(Rules.obtainBuyStrictRuleSet()).usingThreads(4).uuid(uuid).build();

		processorPool.processSymbols(this.symbolsToAnalyze, this.collectingStartLocalDate, date2check,
				YahooDataGatherer.class);
		return BasicResultHolder.getResultsMap(uuid);
	}

	public AnalisysResult checkLooseBuyConditionsForStocks(LocalDate date2check) {
		String uuid = UUID.randomUUID().toString();
		SymbolsProcessorExecutionPool<YahooDataGatherer> processorPool = new SymbolsProcessorExecutionPool.SymbolsProcessorExecutionPoolBuilder<YahooDataGatherer>()
				.usingRules(Rules.obtainBuyLooseRuleSet()).usingThreads(4).uuid(uuid).build();
		processorPool.processSymbols(this.symbolsToAnalyze, this.collectingStartLocalDate, date2check,
				YahooDataGatherer.class);
		return BasicResultHolder.getResultsMap(uuid);
	}

	public AnalisysResult checkStrictSellConditionsForStocks(LocalDate date2check) {
		String uuid = UUID.randomUUID().toString();
		SymbolsProcessorExecutionPool<YahooDataGatherer> processorPool = new SymbolsProcessorExecutionPool.SymbolsProcessorExecutionPoolBuilder<YahooDataGatherer>()
				.usingRules(Rules.obtainSellStrictRuleSet()).usingThreads(4).uuid(uuid).build();
		processorPool.processSymbols(this.symbolsToAnalyze, this.collectingStartLocalDate, date2check,
				YahooDataGatherer.class);
		return BasicResultHolder.getResultsMap(uuid);
	}

}
