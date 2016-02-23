package com.arual.analyzer.test.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.arual.jstock.analyzer.AnalyzerService;
import com.arual.jstock.analyzer.SymbolAnalyzer;
import com.arual.jstock.analyzer.model.AnalisysResult;
import com.arual.jstock.analyzer.model.RuleResult;
import com.arual.jstock.analyzer.model.RuleSet;
import com.arual.jstock.analyzer.model.Rules;
import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.impl.YahooDataGatherer;
import com.arual.jstock.collector.model.Annotations;

public class AnalyzerServiceTest {

	private final static String CHEVRON_SYMBOL = "CVX";
	private final static String AMERICAN_EXPRESS_SYMBOL = "AXP";
	private final static String BOEING_SYMBOL = "BA";
	private final static String CATERPILLAR_SYMBOL = "CAT";
	private final static String CISCO_SYMBOL = "CSCO";
	private final static String DUPONT_SYMBOL = "DD";
	private final static String DISNEY_SYMBOL = "DIS";
	private final static String GENERAL_ELECTRIC_SYMBOL = "GE";
	private final static String AKAMAI_SYMBOL = "AKAM";
	private final static String AMERICAN_AIRLINES_SYMBOL = "AAL";

	private final static Set<String> ALL_SYMBOLS = new HashSet<String>(Arrays.asList(CHEVRON_SYMBOL, DISNEY_SYMBOL,
			GENERAL_ELECTRIC_SYMBOL, AMERICAN_EXPRESS_SYMBOL, BOEING_SYMBOL, CATERPILLAR_SYMBOL, CISCO_SYMBOL, DUPONT_SYMBOL,
			AKAMAI_SYMBOL, AMERICAN_AIRLINES_SYMBOL));

	@Test
	public void analyzeAkamaiStrictBuyDay() {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.JULY, 1);
		Set<String> symbols = new HashSet<String>();
		symbols.add(AKAMAI_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);

		AnalisysResult results = analyzerService.checkStrictBuyConditionsForStocks(date2Check);
		assertTrue("Strict buy OK", results.getResultBySymbol(AKAMAI_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(AKAMAI_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void analyzeAmericanAirlinesStrictBuyDay() throws ParseException {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.MAY, 28);
		Set<String> symbols = new HashSet<String>();
		symbols.add(AMERICAN_AIRLINES_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkStrictBuyConditionsForStocks(date2Check);
		assertTrue("Strict buy OK", results.getResultBySymbol(AMERICAN_AIRLINES_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(AMERICAN_AIRLINES_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void analyzeChevronStrictBuyDay() {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.AUGUST, 27);
		Set<String> symbols = new HashSet<String>();
		symbols.add(CHEVRON_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkStrictBuyConditionsForStocks(date2Check);
		assertTrue("Strict Sell OK", results.getResultBySymbol(CHEVRON_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(CHEVRON_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void analyzeAmericanAirlinesLooseBuyDay() {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.JUNE, 18);
		Set<String> symbols = new HashSet<String>();
		symbols.add(AMERICAN_AIRLINES_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkLooseBuyConditionsForStocks(date2Check);
		assertTrue("Loose buy OK", results.getResultBySymbol(AMERICAN_AIRLINES_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(AMERICAN_AIRLINES_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void analyzeGeneralElectricLooseBuyDay() {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.JULY, 7);
		Set<String> symbols = new HashSet<String>();
		symbols.add(GENERAL_ELECTRIC_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkLooseBuyConditionsForStocks(date2Check);
		assertTrue("Loose buy OK", results.getResultBySymbol(GENERAL_ELECTRIC_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(GENERAL_ELECTRIC_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void testStrictBuySetOfASymbolWhenWeRequestSomeOfThem() throws ParseException {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.AUGUST, 27);
		Set<String> symbols = new HashSet<String>();
		symbols.add(AMERICAN_AIRLINES_SYMBOL);
		symbols.add(BOEING_SYMBOL);
		symbols.add(CHEVRON_SYMBOL);
		symbols.add(CATERPILLAR_SYMBOL);
		symbols.add(DISNEY_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkStrictBuyConditionsForStocks(date2Check);
		assertTrue("Strict buy OK", results.getResultBySymbol(CHEVRON_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(CHEVRON_SYMBOL).getRules().isEmpty());
	}

	@Test
	public void analyzeDisneyStrictSellDay() {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate date2Check = LocalDate.of(2015, Month.JULY, 17);
		Set<String> symbols = new HashSet<String>();
		symbols.add(DISNEY_SYMBOL);
		AnalyzerService analyzerService = new AnalyzerService(startDate, symbols);
		AnalisysResult results = analyzerService.checkStrictSellConditionsForStocks(date2Check);
		System.out.println("EN TEST, vender disney es " + results.getResultBySymbol(DISNEY_SYMBOL).getResult());
		assertTrue("Strict buy OK", results.getResultBySymbol(DISNEY_SYMBOL).getResult());
		assertFalse("Result by rules is not empty", results.getResultBySymbol(DISNEY_SYMBOL).getRules().isEmpty());
	}

	@Ignore
	@Test
	public void analyzeSymbolAXPPositiveTest() throws ParseException {
		LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate endDate = LocalDate.of(2015, Month.OCTOBER, 30);
		LocalDate date2Check = LocalDate.of(2015, Month.MAY, 1);
		String symbol = DISNEY_SYMBOL;
		Set<String> symbols = new HashSet<String>();
		symbols.add(symbol);

		DataGatherer dataGatherer = new YahooDataGatherer();
		List<Annotations> annotationsList = new ArrayList<Annotations>();

		Annotations annotationsAux = dataGatherer.obtainFinancialDataDaily(symbol, startDate, endDate);
		annotationsList.add(annotationsAux);

		SymbolAnalyzer symbolAnalyzer = new SymbolAnalyzer();

		for (int i = 1; i <= 220; i++) {

			RuleSet<RuleResult> result = null;
			try {
				result = symbolAnalyzer.analyze(annotationsList.get(0), Rules.obtainSellStrictRuleSet(), date2Check);
			} catch (Exception e) {
				System.out.println(e + " " + date2Check);
			}

			if (result != null && result.getResult()) {
				System.out.println("-" + date2Check + "->" + result.getResult());
			}
			date2Check = date2Check.plusDays(1);
			// assertFalse("YOu loose", result.getResult());
		}
		System.out.println("Fin");

	}

}
