package com.arual.jstock.analyzer.concurrency;

import java.time.LocalDate;

import com.arual.jstock.analyzer.exception.AnalyzerRuntimeException;
import com.arual.jstock.collector.DataGatherer;

public class DataCollectorRunnable<T extends DataGatherer> implements Runnable {

	private String symbol;

	private LocalDate startDate;

	private LocalDate endDate;
	
	private Class<T> dataGathererClass;

	public DataCollectorRunnable(String symbol, LocalDate startDate, LocalDate endDate, Class<T> dataGathererClass) {
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dataGathererClass = dataGathererClass;
	}

	@Override
	public void run() {
		try {
			DataGatherer dataGatherer = this.dataGathererClass.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			throw new AnalyzerRuntimeException("Exception creating data gatherer:"+e);
		}
		

	}

}
