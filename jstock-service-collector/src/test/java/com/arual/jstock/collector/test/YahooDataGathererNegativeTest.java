/**
 * 
 */
package com.arual.jstock.collector.test;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.exception.NoFinancialDataFoundException;
import com.arual.jstock.collector.impl.YahooDataGatherer;

/**
 * Tests over DataGatherer.
 * 
 * @author Pablo
 * 
 */
public class YahooDataGathererNegativeTest {
	/** year to make tests. */
	private static final int TEST_YEAR = 2014;
	/** first day in month for tests. */
	private static final int TEST_FIRST_DAY_IN_MONTH = 1;
	/** last day in month for tests. */
	private static final int TEST_LAST_DAY_IN_MONTH = 30;

	
	@Test(expected=NoFinancialDataFoundException.class)
	public void nonExistingSymbolTest() {
		DataGatherer dataGatherer = new YahooDataGatherer();
		String symbol = "--";
		LocalDate startDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_FIRST_DAY_IN_MONTH);
		LocalDate endDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_LAST_DAY_IN_MONTH);
		dataGatherer.obtainFinancialDataDaily(symbol, startDate, endDate);	
	}
	
	@Test(expected=NoFinancialDataFoundException.class)
	public void startDateGreaterThanEndDate() {
		DataGatherer dataGatherer = new YahooDataGatherer();
		String symbol = "AXP";
		LocalDate endDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_FIRST_DAY_IN_MONTH);
		LocalDate startDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_LAST_DAY_IN_MONTH);
		dataGatherer.obtainFinancialDataDaily(symbol, startDate, endDate);	
	}

	
}
