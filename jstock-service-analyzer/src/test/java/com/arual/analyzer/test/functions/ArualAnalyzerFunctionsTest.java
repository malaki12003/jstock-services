package com.arual.analyzer.test.functions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.arual.analyzer.test.AbstractAnalyzerTest;
import com.arual.jstock.analyzer.dao.impl.FinanceDAOImpl;
import com.arual.jstock.analyzer.functions.ArualAnalyzerFunctions;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.CrossDirection;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Slope;
import com.arual.jstock.collector.model.Annotation;

public class ArualAnalyzerFunctionsTest extends AbstractAnalyzerTest {

	@Before
	public void initObjects() throws SAXException, IOException, ParserConfigurationException {
		List<TestSymbol> symbolsList = new ArrayList<TestSymbol>();
		symbolsList.add(TestSymbol.AXP);
		symbolsList.add(TestSymbol.ADS_DE);
		symbolsList.add(TestSymbol.BA);
		symbolsList.add(TestSymbol.BMW_DE);
		symbolsList.add(TestSymbol.MCD);
		super.initObjects(symbolsList);
	}

	/**
	 * Test compare value function with RSI indicator.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void compareValueTest() throws ParseException {
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		Map<LocalDate, Double> rsi = financeDao.calculateRSIWithDate(this.getAnnotations().get(TestSymbol.AXP.toString())
				.getAnnotationList(), 14);

		// 11.04.2014 = 33.96013630135567
		LocalDate dateToCheck = LocalDate.of(2014, Month.APRIL, 11);
		boolean val = ArualAnalyzerFunctions.compareValue(rsi, Operator.ELOWER_THAN, 34.0, dateToCheck);
		Assert.assertTrue("Operation 'ELOWER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.LOWER_THAN, 34.0, dateToCheck);
		Assert.assertTrue("Operation 'LOWER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.EGREATER_THAN, 33.9, dateToCheck);
		Assert.assertTrue("Operation 'EGREATER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.GREATER_THAN, 33.9, dateToCheck);
		Assert.assertTrue("Operation 'GREATER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.EQUALS, 33.96013630135567, dateToCheck);
		Assert.assertTrue("Operation 'EQUALS' fails ", val);
		// 09.06.2014 = 76.93212930169989
		dateToCheck = LocalDate.of(2014, Month.JUNE, 9);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.ELOWER_THAN, 77.0, dateToCheck);
		Assert.assertTrue("Operation 'ELOWER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.LOWER_THAN, 77.0, dateToCheck);
		Assert.assertTrue("Operation 'LOWER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.EGREATER_THAN, 76.0, dateToCheck);
		Assert.assertTrue("Operation 'EGREATER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.GREATER_THAN, 76.0, dateToCheck);
		Assert.assertTrue("Operation 'GREATER_THAN' fails ", val);
		val = ArualAnalyzerFunctions.compareValue(rsi, Operator.EQUALS, 76.93212930169989, dateToCheck);
		Assert.assertTrue("Operation 'EQUALS' fails ", val);
	}

	@Test
	public void seriePassAntoherSerieTest() throws ParseException {
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		Map<LocalDate, Double> closingPrices = new TreeMap<LocalDate, Double>();
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		// 14/04/2014 Close supera a bollinger inf hacia arriba
		LocalDate dateToCheck = LocalDate.of(2014, Month.APRIL, 14);

		Map<Indicator, Map<LocalDate, Double>> bollingerMap = financeDao.calculateBollingerBandsWithDate(
				this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList(), 20, 2, 2);
		boolean result = false;
		result = ArualAnalyzerFunctions.seriePassAntoherSerie((TreeMap) closingPrices, (TreeMap) bollingerMap.get(Indicator.BOLLINGER_LOW),
				CrossDirection.UP, 1, dateToCheck);
		Assert.assertTrue("Closing price doesn't cross Bollinger Inf (14/04/2014)", result);

		// 10/03/2014 Close cruza bollinger sup hacia abajo
		dateToCheck = LocalDate.of(2014, Month.MARCH, 10);
		result = ArualAnalyzerFunctions.seriePassAntoherSerie((TreeMap) closingPrices,
				(TreeMap) bollingerMap.get(Indicator.BOLLINGER_HIGH), CrossDirection.DOWN, 2, dateToCheck);
		Assert.assertTrue("Closing price doesn't cross Bollinger Sup (10/03/2014)", result);

		// 10/06/2014 Close cruza bollinger sup hacia abajo
		dateToCheck = LocalDate.of(2014, Month.JUNE, 10);
		result = ArualAnalyzerFunctions.seriePassAntoherSerie((TreeMap) closingPrices,
				(TreeMap) bollingerMap.get(Indicator.BOLLINGER_HIGH), CrossDirection.DOWN, 2, dateToCheck);
		Assert.assertTrue("Closing price doesn't cross Bollinger Sup (10/06/2014)", result);

		// 17/06/2014 Close NO cruza bollinger sup hacia abajo
		dateToCheck = LocalDate.of(2014, Month.JUNE, 17);
		result = ArualAnalyzerFunctions.seriePassAntoherSerie((TreeMap) closingPrices,
				(TreeMap) bollingerMap.get(Indicator.BOLLINGER_HIGH), CrossDirection.DOWN, 2, dateToCheck);
		Assert.assertFalse("Closing price doesn't cross Bollinger Sup (17/06/2014)", result);

		// 07/05/2014 close cruza b mid hacia arriba
		dateToCheck = LocalDate.of(2014, Month.MAY, 7);
		result = ArualAnalyzerFunctions.seriePassAntoherSerie((TreeMap) closingPrices, (TreeMap) bollingerMap.get(Indicator.BOLLINGER_MID),
				CrossDirection.UP, 1, dateToCheck);
		Assert.assertTrue("Closing price doesn't cross Bollinger Sup (10/06/2014)", result);

	}

	@Test
	public void slopeCheckTest() throws ParseException {
		TreeMap<LocalDate, Double> closingPrices = new TreeMap<LocalDate, Double>();
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		// 14/04/2014 Close supera a bollinger inf hacia arriba
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate dateToCheck = LocalDate.of(2014, Month.APRIL, 14);
		boolean result = ArualAnalyzerFunctions.checkTendency(closingPrices, dateToCheck, 20, Slope.DESCENDING, 0.16);
		Assert.assertTrue("Closing price doesn't have a descending slope (10/06/2014)", result);

	}
}
