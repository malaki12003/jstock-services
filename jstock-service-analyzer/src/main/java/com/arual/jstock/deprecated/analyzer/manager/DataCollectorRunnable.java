package com.arual.jstock.deprecated.analyzer.manager;

import java.time.LocalDate;
import java.util.Queue;

import com.arual.jstock.analyzer.model.SymbolData;
import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.impl.YahooDataGatherer;
import com.arual.jstock.collector.model.Annotations;

public class DataCollectorRunnable implements Runnable {

	private final String symbol;
	// private final ConcurrentHashMap<String, Map<Date, Annotation>>
	// annotationsMap;
	private final Queue<SymbolData> annotationsQueue;
	private final DataGatherer collector;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final Long millisToSleep;

	/**
	 * @return the millisToSleep
	 */
	public Long getMillisToSleep() {
		return millisToSleep;
	}

	public DataCollectorRunnable(final String symbol, final Queue<SymbolData> annotationsQueue, final LocalDate startDate, final LocalDate endDate,
			final Long millisToSleep) {
		super();
		this.symbol = symbol;
		this.annotationsQueue = annotationsQueue;
		this.collector = new YahooDataGatherer();
		this.startDate = startDate;
		this.endDate = endDate;
		this.millisToSleep = millisToSleep;
	}

	@Override
	public void run() {
		
			Annotations resultAnotations = this.collector.obtainFinancialDataDaily(this.symbol, this.startDate, this.endDate);
			if (resultAnotations != null) {
				SymbolData sd = new SymbolData(symbol, resultAnotations);
				this.annotationsQueue.add(sd);
			}
		
	}
}
