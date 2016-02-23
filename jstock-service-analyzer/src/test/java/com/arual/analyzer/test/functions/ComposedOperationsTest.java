/**
 * 
 */
package com.arual.analyzer.test.functions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.arual.jstock.analyzer.functions.AbstractFinancialAnalyzerFunction;
import com.arual.jstock.analyzer.functions.BuySlopeStairValidAnalyzerFunction;
import com.arual.jstock.analyzer.functions.CompareValueAnalyzerFunction;
import com.arual.jstock.analyzer.functions.SellSlopeStairValidAnalyzerFunction;
import com.arual.jstock.analyzer.functions.SeriePassAnotherAnalyzerFunction;
import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.CrossDirection;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;
import com.arual.jstock.collector.model.Annotation;

/**
 * Test to check Composed functions.
 * 
 * @author Pablo
 * 
 */
public class ComposedOperationsTest extends AbstractAnalyzerTest {
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
	 * Compare value in composed mode test
	 * 
	 * @throws ParseException
	 */
	@Test
	public void compareValueFunctionTest() throws ParseException {
		AbstractFinancialAnalyzerFunction composedFunction = new CompareValueAnalyzerFunction();
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		TreeMap<LocalDate, Double> rsi = (TreeMap<LocalDate, Double>) financeDao.calculateRSIWithDate(
				this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList(), 14);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		// 11.04.2014 = 33.96013630135567
		LocalDate dateToCheck = LocalDate.of(2014, Month.APRIL, 11);
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();

		Parameter serieParam = new Parameter();
		serieParam.setName(ParameterName.SERIE);
		serieParam.setValue(Indicator.RSI);
		parameters.put(ParameterName.SERIE.toString(), serieParam);

		Parameter operatorParam = new Parameter();
		operatorParam.setName(ParameterName.OPERATION);
		operatorParam.setValue(Operator.ELOWER_THAN);
		parameters.put(ParameterName.OPERATION.toString(), operatorParam);

		Parameter thresholdParam = new Parameter();
		thresholdParam.setName(ParameterName.THRESHOLD);
		thresholdParam.setValue(34.0);
		parameters.put(ParameterName.THRESHOLD.toString(), thresholdParam);

		Map<Indicator, TreeMap<LocalDate, Double>> indicators = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		indicators.put(Indicator.RSI, rsi);

		boolean val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("Operation 'ELOWER_THAN' fails ", val);
		/* ****************************************************** */
		// 09.06.2014 = 76.93212930169989
		dateToCheck = LocalDate.of(2014, Month.JUNE, 9);
		serieParam = new Parameter();
		serieParam.setName(ParameterName.SERIE);
		serieParam.setValue(Indicator.RSI);
		parameters.put(ParameterName.SERIE.toString(), serieParam);

		operatorParam = new Parameter();
		operatorParam.setName(ParameterName.OPERATION);
		operatorParam.setValue(Operator.EQUALS);
		parameters.put(ParameterName.OPERATION.toString(), operatorParam);

		thresholdParam = new Parameter();
		thresholdParam.setName(ParameterName.THRESHOLD);
		thresholdParam.setValue(76.93212930169989);
		parameters.put(ParameterName.THRESHOLD.toString(), thresholdParam);
		val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("Operation 'EQUALS' fails ", val);

	}

	@Test
	public void seriePassAnotherSerieTest() throws ParseException {
		AbstractFinancialAnalyzerFunction composedFunction = new SeriePassAnotherAnalyzerFunction();
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		TreeMap<LocalDate, Double> bollingerInf = null;

		TreeMap<LocalDate, Double> closingPrices = null;
		closingPrices = new TreeMap<LocalDate, Double>();

		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		Parameter serie1Param = null;
		Parameter serie2Param = null;
		Parameter daysBackParam = null;
		Parameter directionParam = null;
		LocalDate dateToCheck = null;
		Parameter dateParam = null;
		boolean val = false;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Map<Indicator, TreeMap<LocalDate, Double>> indicators = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		// Closing supera Bollinger Inf para MCD el 04/02/2014
		bollingerInf = (TreeMap<LocalDate, Double>) financeDao.calculateBollingerBandsWithDate(
				this.getAnnotations().get(TestSymbol.MCD.toString()).getAnnotationList(), 20, 2, 2).get(Indicator.BOLLINGER_LOW);
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.MCD.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		serie1Param = new Parameter();
		serie1Param.setName(ParameterName.SERIE1);
		serie1Param.setValue(Indicator.CLOSE);
		parameters.put(ParameterName.SERIE1.toString(), serie1Param);

		serie2Param = new Parameter();
		serie2Param.setName(ParameterName.SERIE2);
		serie2Param.setValue(Indicator.BOLLINGER_LOW);
		parameters.put(ParameterName.SERIE2.toString(), serie2Param);

		indicators.put(Indicator.CLOSE, closingPrices);
		indicators.put(Indicator.BOLLINGER_LOW, bollingerInf);

		daysBackParam = new Parameter();
		daysBackParam.setName(ParameterName.DAYS_BACK);
		daysBackParam.setValue(2);
		parameters.put(ParameterName.DAYS_BACK.toString(), daysBackParam);

		directionParam = new Parameter();
		directionParam.setName(ParameterName.DIRECTION);
		directionParam.setValue(CrossDirection.UP);
		parameters.put(ParameterName.DIRECTION.toString(), directionParam);

		dateToCheck = LocalDate.of(2014, Month.FEBRUARY, 4);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);

		Assert.assertTrue("Closing no supera Bollinger Inf para MCD el 04/02/2014 ", val);

		// 14/04/2014 AXP Close supera a bollinger inf hacia arriba
		bollingerInf = null;

		closingPrices = new TreeMap<LocalDate, Double>();

		parameters = new HashMap<String, Parameter>();

		bollingerInf = (TreeMap<LocalDate, Double>) financeDao.calculateBollingerBandsWithDate(
				new ArrayList<Annotation>(this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()), 20, 2, 2).get(
				Indicator.BOLLINGER_LOW);
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		serie1Param = new Parameter();
		serie1Param.setName(ParameterName.SERIE1);
		serie1Param.setValue(Indicator.CLOSE);
		parameters.put(ParameterName.SERIE1.toString(), serie1Param);

		serie2Param = new Parameter();
		serie2Param.setName(ParameterName.SERIE2);
		serie2Param.setValue(Indicator.BOLLINGER_LOW);
		parameters.put(ParameterName.SERIE2.toString(), serie2Param);

		indicators = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		indicators.put(Indicator.CLOSE, closingPrices);
		indicators.put(Indicator.BOLLINGER_LOW, bollingerInf);

		daysBackParam = new Parameter();
		daysBackParam.setName(ParameterName.DAYS_BACK);
		daysBackParam.setValue(1);
		parameters.put(ParameterName.DAYS_BACK.toString(), daysBackParam);

		directionParam = new Parameter();
		directionParam.setName(ParameterName.DIRECTION);
		directionParam.setValue(CrossDirection.UP);
		parameters.put(ParameterName.DIRECTION.toString(), directionParam);

		dateToCheck = LocalDate.of(2014, Month.APRIL, 14);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);

		Assert.assertTrue("Closing no supera Bollinger Inf para AXP el 14/04/2014 ", val);
	}

	@Test
	public void SellSlopeStairValidAnalyzerFunctionTest1AXP() throws ParseException {
		AbstractFinancialAnalyzerFunction composedFunction = new SellSlopeStairValidAnalyzerFunction();
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		TreeMap<LocalDate, Double> closingPrices = null;
		closingPrices = new TreeMap<LocalDate, Double>();
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		Parameter serieParam = null;
		LocalDate dateToCheck = null;
		Parameter dateParam = null;
		boolean val = false;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Map<Indicator, TreeMap<LocalDate, Double>> indicators = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		serieParam = new Parameter();
		serieParam.setName(ParameterName.SERIE);
		serieParam.setValue(Indicator.CLOSE);
		parameters.put(ParameterName.SERIE.toString(), serieParam);
		indicators.put(Indicator.CLOSE, closingPrices);
		dateToCheck = LocalDate.of(2014, Month.JUNE, 10);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("AXP no supera el sell test slope el 10/06/2014 ", val);

		// AXP 10/03/2014
		dateToCheck = LocalDate.of(2014, Month.MARCH, 10);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("AXP no supera el sell test slope el 10/03/2014 ", val);

	}

	@Test
	public void BuySlopeStairValidAnalyzerFunctionTest1AXP() throws ParseException {
		AbstractFinancialAnalyzerFunction composedFunction = new BuySlopeStairValidAnalyzerFunction();
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		TreeMap<LocalDate, Double> closingPrices = null;
		closingPrices = new TreeMap<LocalDate, Double>();
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		Parameter serieParam = null;
		LocalDate dateToCheck = null;
		Parameter dateParam = null;
		boolean val = false;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Map<Indicator, TreeMap<LocalDate, Double>> indicators = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		serieParam = new Parameter();
		serieParam.setName(ParameterName.SERIE);
		serieParam.setValue(Indicator.CLOSE);
		parameters.put(ParameterName.SERIE.toString(), serieParam);
		indicators.put(Indicator.CLOSE, closingPrices);
		dateToCheck = LocalDate.of(2014, Month.APRIL, 14);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("AXP no supera el buy test slope el 14/04/2014 ", val);

		// BA 07/08/2014
		parameters = new HashMap<String, Parameter>();
		closingPrices = new TreeMap<LocalDate, Double>();
		for (Annotation annotation : this.getAnnotations().get(TestSymbol.BA.toString()).getAnnotationList()) {
			closingPrices.put(annotation.getDate(), annotation.getClose());
		}
		serieParam = new Parameter();
		serieParam.setName(ParameterName.SERIE);
		serieParam.setValue(Indicator.CLOSE);
		parameters.put(ParameterName.SERIE.toString(), serieParam);
		indicators.put(Indicator.CLOSE, closingPrices);

		dateToCheck = LocalDate.of(2014, Month.AUGUST, 7);

		val = composedFunction.analyze(parameters, indicators, dateToCheck);
		Assert.assertTrue("BA no supera el buy test slope el 07/08/2014 ", val);

	}

	public static void main(final String[] args) throws UnsupportedEncodingException, MalformedURLException, ParserConfigurationException,
			SAXException, IOException, ParseException {

		ComposedOperationsTest cot = new ComposedOperationsTest();
		cot.initObjects();
		// cot.compareValueFunctionTest();
		// cot.seriePassAnotherSerieTest();
		cot.BuySlopeStairValidAnalyzerFunctionTest1AXP();
	}
}
