/**
 * 
 */
package com.arual.jstock.collector.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.impl.YahooDataGatherer;
import com.arual.jstock.collector.model.Annotation;
import com.arual.jstock.collector.model.Annotations;
import com.google.common.collect.Ordering;

/**
 * Tests over DataGatherer.
 * 
 * @author Pablo
 * 
 */
public class YahooDataGathererTest {
	/** year to make tests. */
	private static final int TEST_YEAR = 2014;
	/** first day in month for tests. */
	private static final int TEST_FIRST_DAY_IN_MONTH = 1;
	/** last day in month for tests. */
	private static final int TEST_LAST_DAY_IN_MONTH = 30;

	/**
	 * General purpose test over IDataGatherer results.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void dataObtainedForASymbolBetween2Dates() {
		DataGatherer dataGatherer = new YahooDataGatherer();

		Annotations annotations = null;
		String symbol = "AXP";

		LocalDate startDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_FIRST_DAY_IN_MONTH);
		LocalDate endDate = LocalDate.of(TEST_YEAR, Month.JANUARY.getValue(), TEST_LAST_DAY_IN_MONTH);

		annotations = dataGatherer.obtainFinancialDataDaily(symbol, startDate, endDate);
		assertNotNull(annotations);

		List<Annotation> annotationsList = annotations.getAnnotationList();

		assertTrue(annotationsList.get(0).getDate().compareTo(annotationsList.get(annotationsList.size() - 1).getDate()) < 0);
		assertTrue(annotationsList.get(0).getHigh() >= annotationsList.get(0).getLow());
		assertTrue(annotationsList.get(0).getHigh() >= annotationsList.get(0).getClose());
		assertTrue(annotationsList.get(0).getHigh() >= annotationsList.get(0).getOpen());
		assertTrue(annotationsList.get(0).getLow() <= annotationsList.get(0).getClose());
		assertTrue(annotationsList.get(0).getLow() <= annotationsList.get(0).getOpen());

		assertTrue(Ordering.natural().isOrdered(annotationsList));
	}

	/**
	 * With this test, we want to check that yesterday result is obtained,
	 * whenever yesterday was not saturday or sunday.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void checkLastDayResult() {
		DataGatherer dataGatherer = new YahooDataGatherer();

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		while (dayIsOnWeekEnd(endDate)) {
			endDate = endDate.minusDays(1);
		}
		startDate = endDate;

		startDate = startDate.minusDays(5);

		Annotations result = null;
		String symbol = "AXP";
		result = dataGatherer.obtainFinancialDataDaily(symbol, startDate, endDate);

		LocalDate dateToCheck = LocalDate.now();
		while (dayIsOnWeekEnd(dateToCheck)) {
			dateToCheck = dateToCheck.minusDays(1);
		}
		List<Annotation> quotesList = result.getAnnotationList();
		System.out.println("--->" + quotesList.get(quotesList.size() - 1).getDate());
		System.out.println("--->" + dateToCheck);
		LocalDate auxDate = LocalDate.now();
		auxDate = quotesList.get(quotesList.size() - 1).getDate();
		assertTrue(auxDate.getDayOfYear() == dateToCheck.getDayOfYear());

	}

	private boolean dayIsOnWeekEnd(LocalDate day) {
		boolean isOnWeekend = false;
		if (DayOfWeek.SATURDAY.equals(day.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(day.getDayOfWeek())) {
			isOnWeekend = true;
		}
		return isOnWeekend;
	}
}
