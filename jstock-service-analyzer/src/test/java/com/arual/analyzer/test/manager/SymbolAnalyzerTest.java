package com.arual.analyzer.test.manager;


public class SymbolAnalyzerTest {// extends AbstractAnalyzerTest {
    /*
     * @Before public void initObjects() throws SAXException, IOException,
     * ParserConfigurationException { List<TestSymbol> symbolsList = new
     * ArrayList<TestSymbol>(); symbolsList.add(TestSymbol.AXP);
     * symbolsList.add(TestSymbol.ADS_DE); symbolsList.add(TestSymbol.BA);
     * symbolsList.add(TestSymbol.BMW_DE); symbolsList.add(TestSymbol.MCD);
     * super.initObjects(symbolsList); }
     * 
     * @Test public void axpAnalyzeTest() throws ParseException {
     * 
     * SymbolAnalyzer analyzer = new SymbolAnalyzer(TestSymbol.AXP.toString(),
     * this.getAnnotations().get(TestSymbol.AXP.toString()));
     * 
     * SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date
     * dateToCheck = df.parse("14/04/2014"); RuleSet ruleSet = new RuleSet();
     * ruleSet.setName("Buy full test");
     * 
     * List<Rule> rules = new ArrayList<Rule>(); // Bollinger params Rule
     * bollingerRule = new Rule(); Map<String, Parameter> bollingerParameters =
     * new HashMap<String, Parameter>();
     * 
     * Parameter bollSerie1Param = new Parameter();
     * bollSerie1Param.setName(ParameterName.SERIE1);
     * bollSerie1Param.setValue(Indicator.CLOSE);
     * bollingerParameters.put(ParameterName.SERIE1.toString(),
     * bollSerie1Param);
     * 
     * Parameter bollSerie2Param = new Parameter();
     * bollSerie2Param.setName(ParameterName.SERIE2);
     * bollSerie2Param.setValue(Indicator.BOLLINGER_LOW);
     * bollingerParameters.put(ParameterName.SERIE2.toString(),
     * bollSerie2Param);
     * 
     * Parameter bollDaysBackParam = new Parameter();
     * bollDaysBackParam.setName(ParameterName.DAYS_BACK);
     * bollDaysBackParam.setValue(1);
     * bollingerParameters.put(ParameterName.DAYS_BACK.toString(),
     * bollDaysBackParam);
     * 
     * Parameter bollDirectionParam = new Parameter();
     * bollDirectionParam.setName(ParameterName.DIRECTION);
     * bollDirectionParam.setValue(CrossDirection.UP);
     * bollingerParameters.put(ParameterName.DIRECTION.toString(),
     * bollDirectionParam);
     * 
     * Parameter dateToCheckParam = new Parameter();
     * dateToCheckParam.setName(ParameterName.DATE_TO_CHECK);
     * dateToCheckParam.setValue(dateToCheck);
     * bollingerParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * bollingerRule.setParameters(bollingerParameters);
     * bollingerRule.setOperation(Operation.LINE_CROSS);
     * rules.add(bollingerRule);
     * 
     * // RSI rule Map<String, Parameter> rsiParameters = new HashMap<String,
     * Parameter>(); Rule rsiRule = new Rule(); Parameter rsiSerieParam = new
     * Parameter(); rsiSerieParam.setName(ParameterName.SERIE);
     * rsiSerieParam.setValue(Indicator.RSI);
     * rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);
     * 
     * Parameter rsiOperatorParam = new Parameter();
     * rsiOperatorParam.setName(ParameterName.OPERATION);
     * rsiOperatorParam.setValue(Operator.ELOWER_THAN);
     * rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);
     * 
     * Parameter rsiDateParam = new Parameter();
     * rsiDateParam.setName(ParameterName.DATE_TO_CHECK);
     * rsiDateParam.setValue(dateToCheck);
     * rsiParameters.put(ParameterName.DATE_TO_CHECK.toString(), rsiDateParam);
     * 
     * Parameter rsiThresholdParam = new Parameter();
     * rsiThresholdParam.setName(ParameterName.THRESHOLD);
     * rsiThresholdParam.setValue(39.5); rsiParameters
     * .put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
     * rsiRule.setParameters(rsiParameters);
     * rsiRule.setOperation(Operation.COMPARE_VALUE); rules.add(rsiRule);
     * 
     * // Stochastyc operation Rule stochastycRule = new Rule(); Map<String,
     * Parameter> stochastycParameters = new HashMap<String, Parameter>();
     * 
     * Parameter kParam = new Parameter(); kParam.setName(ParameterName.SERIE1);
     * kParam.setValue(Indicator.STOCHASTYC_K);
     * stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);
     * 
     * Parameter dParam = new Parameter(); dParam.setName(ParameterName.SERIE2);
     * dParam.setValue(Indicator.STOCHASTYK_D);
     * stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);
     * 
     * Parameter stochDaysBackParam = new Parameter();
     * stochDaysBackParam.setName(ParameterName.DAYS_BACK);
     * stochDaysBackParam.setValue(1);
     * stochastycParameters.put(ParameterName.DAYS_BACK.toString(),
     * stochDaysBackParam);
     * 
     * Parameter stochDirectionParam = new Parameter();
     * stochDirectionParam.setName(ParameterName.DIRECTION);
     * stochDirectionParam.setValue(CrossDirection.UP);
     * stochastycParameters.put(ParameterName.DIRECTION.toString(),
     * stochDirectionParam);
     * 
     * stochastycParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * stochastycRule.setParameters(stochastycParameters);
     * stochastycRule.setOperation(Operation.LINE_CROSS);
     * rules.add(stochastycRule);
     * 
     * // Buy slope test Rule slopeRule = new Rule(); Parameter slopeSerie = new
     * Parameter(); Map<String, Parameter> slopeParameters = new HashMap<String,
     * Parameter>(); slopeSerie.setName(ParameterName.SERIE);
     * slopeSerie.setValue(Indicator.CLOSE);
     * slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);
     * 
     * Parameter slopeDateParam = new Parameter();
     * slopeDateParam.setName(ParameterName.DATE_TO_CHECK);
     * slopeDateParam.setValue(dateToCheck);
     * slopeParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * slopeDateParam); slopeRule.setOperation(Operation.BUY_SLOPE_TEST);
     * slopeRule.setParameters(slopeParameters);
     * 
     * rules.add(slopeRule);
     * 
     * ruleSet.setRules(rules);
     * 
     * boolean result = analyzer.analyze(ruleSet, dateToCheck);
     * Assert.assertTrue("AXP debería ser comprado el 14/04/2014 ", result); }
     * 
     * @Test public void baAnalyzeTest() throws ParseException {
     * 
     * SymbolAnalyzer analyzer = new SymbolAnalyzer(TestSymbol.BA.toString(),
     * this.getAnnotations().get(TestSymbol.BA.toString()));
     * 
     * SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date
     * dateToCheck = df.parse("07/08/2014");
     * 
     * List<Rule> rules = new ArrayList<Rule>(); // Bollinger params // Rule
     * bollingerRule = new Rule(); // Map<String, Parameter> bollingerParameters
     * = new HashMap<String, // Parameter>(); // // Parameter bollSerie1Param =
     * new Parameter(); // bollSerie1Param.setName(ParameterName.SERIE1); //
     * bollSerie1Param.setValue(Indicator.CLOSE); //
     * bollingerParameters.put(ParameterName.SERIE1.toString(), //
     * bollSerie1Param); // // Parameter bollSerie2Param = new Parameter(); //
     * bollSerie2Param.setName(ParameterName.SERIE2); //
     * bollSerie2Param.setValue(Indicator.BOLLINGER_LOW); //
     * bollingerParameters.put(ParameterName.SERIE2.toString(), //
     * bollSerie2Param); // // Parameter bollDaysBackParam = new Parameter(); //
     * bollDaysBackParam.setName(ParameterName.DAYS_BACK); //
     * bollDaysBackParam.setValue(1); //
     * bollingerParameters.put(ParameterName.DAYS_BACK.toString(), //
     * bollDaysBackParam); // // Parameter bollDirectionParam = new Parameter();
     * // bollDirectionParam.setName(ParameterName.DIRECTION); //
     * bollDirectionParam.setValue(CrossDirection.UP); //
     * bollingerParameters.put(ParameterName.DIRECTION.toString(), //
     * bollDirectionParam);
     * 
     * Parameter dateToCheckParam = new Parameter();
     * dateToCheckParam.setName(ParameterName.DATE_TO_CHECK);
     * dateToCheckParam.setValue(dateToCheck); //
     * bollingerParameters.put(ParameterName.DATE_TO_CHECK.toString(), //
     * dateToCheckParam);
     * 
     * // bollingerRule.setParameters(bollingerParameters); //
     * bollingerRule.setOperation(Operation.LINE_CROSS); //
     * rules.add(bollingerRule);
     * 
     * // RSI rule Map<String, Parameter> rsiParameters = new HashMap<String,
     * Parameter>(); Rule rsiRule = new Rule(); Parameter rsiSerieParam = new
     * Parameter(); rsiSerieParam.setName(ParameterName.SERIE);
     * rsiSerieParam.setValue(Indicator.RSI);
     * rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);
     * 
     * Parameter rsiOperatorParam = new Parameter();
     * rsiOperatorParam.setName(ParameterName.OPERATION);
     * rsiOperatorParam.setValue(Operator.ELOWER_THAN);
     * rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);
     * 
     * Parameter rsiDateParam = new Parameter();
     * rsiDateParam.setName(ParameterName.DATE_TO_CHECK);
     * rsiDateParam.setValue(dateToCheck);
     * rsiParameters.put(ParameterName.DATE_TO_CHECK.toString(), rsiDateParam);
     * 
     * Parameter rsiThresholdParam = new Parameter();
     * rsiThresholdParam.setName(ParameterName.THRESHOLD);
     * rsiThresholdParam.setValue(39.5); rsiParameters
     * .put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
     * rsiRule.setParameters(rsiParameters);
     * rsiRule.setOperation(Operation.COMPARE_VALUE); rules.add(rsiRule);
     * 
     * // Stochastyc operation Rule stochastycRule = new Rule(); Map<String,
     * Parameter> stochastycParameters = new HashMap<String, Parameter>();
     * 
     * Parameter kParam = new Parameter(); kParam.setName(ParameterName.SERIE1);
     * kParam.setValue(Indicator.STOCHASTYC_K);
     * stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);
     * 
     * Parameter dParam = new Parameter(); dParam.setName(ParameterName.SERIE2);
     * dParam.setValue(Indicator.STOCHASTYK_D);
     * stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);
     * 
     * Parameter stochDaysBackParam = new Parameter();
     * stochDaysBackParam.setName(ParameterName.DAYS_BACK);
     * stochDaysBackParam.setValue(1);
     * stochastycParameters.put(ParameterName.DAYS_BACK.toString(),
     * stochDaysBackParam);
     * 
     * Parameter stochDirectionParam = new Parameter();
     * stochDirectionParam.setName(ParameterName.DIRECTION);
     * stochDirectionParam.setValue(CrossDirection.UP);
     * stochastycParameters.put(ParameterName.DIRECTION.toString(),
     * stochDirectionParam);
     * 
     * stochastycParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * stochastycRule.setParameters(stochastycParameters);
     * stochastycRule.setOperation(Operation.LINE_CROSS);
     * rules.add(stochastycRule);
     * 
     * // Buy slope test Rule slopeRule = new Rule(); Parameter slopeSerie = new
     * Parameter(); Map<String, Parameter> slopeParameters = new HashMap<String,
     * Parameter>(); slopeSerie.setName(ParameterName.SERIE);
     * slopeSerie.setValue(Indicator.CLOSE);
     * slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);
     * 
     * Parameter slopeDateParam = new Parameter();
     * slopeDateParam.setName(ParameterName.DATE_TO_CHECK);
     * slopeDateParam.setValue(dateToCheck);
     * slopeParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * slopeDateParam); slopeRule.setOperation(Operation.BUY_SLOPE_TEST);
     * slopeRule.setParameters(slopeParameters);
     * 
     * rules.add(slopeRule); RuleSet ruleSet = new RuleSet();
     * ruleSet.setName("Buy full test"); ruleSet.setRules(rules); boolean result
     * = analyzer.analyze(ruleSet, dateToCheck);
     * Assert.assertTrue("BA debería ser comprado el 07/08/2014 ", result); }
     * 
     * @Test public void axpSellAnalyzeTest() throws ParseException {
     * 
     * SymbolAnalyzer analyzer = new SymbolAnalyzer(TestSymbol.AXP.toString(),
     * this.getAnnotations().get(TestSymbol.AXP.toString()));
     * 
     * SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date
     * dateToCheck = df.parse("10/06/2014");
     * 
     * List<Rule> rules = new ArrayList<Rule>(); // Bollinger params Rule
     * bollingerRule = new Rule(); Map<String, Parameter> bollingerParameters =
     * new HashMap<String, Parameter>();
     * 
     * Parameter bollSerie1Param = new Parameter();
     * bollSerie1Param.setName(ParameterName.SERIE1);
     * bollSerie1Param.setValue(Indicator.CLOSE);
     * bollingerParameters.put(ParameterName.SERIE1.toString(),
     * bollSerie1Param);
     * 
     * Parameter bollSerie2Param = new Parameter();
     * bollSerie2Param.setName(ParameterName.SERIE2);
     * bollSerie2Param.setValue(Indicator.BOLLINGER_HIGH);
     * bollingerParameters.put(ParameterName.SERIE2.toString(),
     * bollSerie2Param);
     * 
     * Parameter bollDaysBackParam = new Parameter();
     * bollDaysBackParam.setName(ParameterName.DAYS_BACK);
     * bollDaysBackParam.setValue(1);
     * bollingerParameters.put(ParameterName.DAYS_BACK.toString(),
     * bollDaysBackParam);
     * 
     * Parameter bollDirectionParam = new Parameter();
     * bollDirectionParam.setName(ParameterName.DIRECTION);
     * bollDirectionParam.setValue(CrossDirection.DOWN);
     * bollingerParameters.put(ParameterName.DIRECTION.toString(),
     * bollDirectionParam);
     * 
     * Parameter dateToCheckParam = new Parameter();
     * dateToCheckParam.setName(ParameterName.DATE_TO_CHECK);
     * dateToCheckParam.setValue(dateToCheck);
     * bollingerParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * bollingerRule.setParameters(bollingerParameters);
     * bollingerRule.setOperation(Operation.LINE_CROSS);
     * rules.add(bollingerRule);
     * 
     * // RSI rule Map<String, Parameter> rsiParameters = new HashMap<String,
     * Parameter>(); Rule rsiRule = new Rule(); Parameter rsiSerieParam = new
     * Parameter(); rsiSerieParam.setName(ParameterName.SERIE);
     * rsiSerieParam.setValue(Indicator.RSI);
     * rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);
     * 
     * Parameter rsiOperatorParam = new Parameter();
     * rsiOperatorParam.setName(ParameterName.OPERATION);
     * rsiOperatorParam.setValue(Operator.EGREATER_THAN);
     * rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);
     * 
     * Parameter rsiDateParam = new Parameter();
     * rsiDateParam.setName(ParameterName.DATE_TO_CHECK);
     * rsiDateParam.setValue(dateToCheck);
     * rsiParameters.put(ParameterName.DATE_TO_CHECK.toString(), rsiDateParam);
     * 
     * Parameter rsiThresholdParam = new Parameter();
     * rsiThresholdParam.setName(ParameterName.THRESHOLD);
     * rsiThresholdParam.setValue(63D); rsiParameters
     * .put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
     * rsiRule.setParameters(rsiParameters);
     * rsiRule.setOperation(Operation.COMPARE_VALUE); rules.add(rsiRule);
     * 
     * // Stochastyc operation Rule stochastycRule = new Rule(); Map<String,
     * Parameter> stochastycParameters = new HashMap<String, Parameter>();
     * 
     * Parameter kParam = new Parameter(); kParam.setName(ParameterName.SERIE1);
     * kParam.setValue(Indicator.STOCHASTYC_K);
     * stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);
     * 
     * Parameter dParam = new Parameter(); dParam.setName(ParameterName.SERIE2);
     * dParam.setValue(Indicator.STOCHASTYK_D);
     * stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);
     * 
     * Parameter stochDaysBackParam = new Parameter();
     * stochDaysBackParam.setName(ParameterName.DAYS_BACK);
     * stochDaysBackParam.setValue(2);
     * stochastycParameters.put(ParameterName.DAYS_BACK.toString(),
     * stochDaysBackParam);
     * 
     * Parameter stochDirectionParam = new Parameter();
     * stochDirectionParam.setName(ParameterName.DIRECTION);
     * stochDirectionParam.setValue(CrossDirection.DOWN);
     * stochastycParameters.put(ParameterName.DIRECTION.toString(),
     * stochDirectionParam);
     * 
     * stochastycParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * stochastycRule.setParameters(stochastycParameters);
     * stochastycRule.setOperation(Operation.LINE_CROSS);
     * rules.add(stochastycRule);
     * 
     * // Sell slope test Rule slopeRule = new Rule(); Parameter slopeSerie =
     * new Parameter(); Map<String, Parameter> slopeParameters = new
     * HashMap<String, Parameter>(); slopeSerie.setName(ParameterName.SERIE);
     * slopeSerie.setValue(Indicator.CLOSE);
     * slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);
     * 
     * Parameter slopeDateParam = new Parameter();
     * slopeDateParam.setName(ParameterName.DATE_TO_CHECK);
     * slopeDateParam.setValue(dateToCheck);
     * slopeParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * slopeDateParam); slopeRule.setOperation(Operation.SELL_SLOPE_TEST);
     * slopeRule.setParameters(slopeParameters);
     * 
     * rules.add(slopeRule);
     * 
     * RuleSet ruleSet = new RuleSet(); ruleSet.setName("Sell full test");
     * ruleSet.setRules(rules); boolean result = analyzer.analyze(ruleSet,
     * dateToCheck); Assert.assertTrue("AXP debería ser vendida el 14/06/2014 ",
     * result); }
     * 
     * @Test public void mcdSellAnalyzeTest() throws ParseException {
     * 
     * SymbolAnalyzer analyzer = new SymbolAnalyzer(TestSymbol.MCD.toString(),
     * this.getAnnotations().get(TestSymbol.MCD.toString()));
     * 
     * SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); Date
     * dateToCheck = df.parse("14/05/2014");
     * 
     * List<Rule> rules = new ArrayList<Rule>(); // Bollinger params Rule
     * bollingerRule = new Rule(); Map<String, Parameter> bollingerParameters =
     * new HashMap<String, Parameter>();
     * 
     * Parameter bollSerie1Param = new Parameter();
     * bollSerie1Param.setName(ParameterName.SERIE1);
     * bollSerie1Param.setValue(Indicator.CLOSE);
     * bollingerParameters.put(ParameterName.SERIE1.toString(),
     * bollSerie1Param);
     * 
     * Parameter bollSerie2Param = new Parameter();
     * bollSerie2Param.setName(ParameterName.SERIE2);
     * bollSerie2Param.setValue(Indicator.BOLLINGER_HIGH);
     * bollingerParameters.put(ParameterName.SERIE2.toString(),
     * bollSerie2Param);
     * 
     * Parameter bollDaysBackParam = new Parameter();
     * bollDaysBackParam.setName(ParameterName.DAYS_BACK);
     * bollDaysBackParam.setValue(2);
     * bollingerParameters.put(ParameterName.DAYS_BACK.toString(),
     * bollDaysBackParam);
     * 
     * Parameter bollDirectionParam = new Parameter();
     * bollDirectionParam.setName(ParameterName.DIRECTION);
     * bollDirectionParam.setValue(CrossDirection.DOWN);
     * bollingerParameters.put(ParameterName.DIRECTION.toString(),
     * bollDirectionParam);
     * 
     * Parameter dateToCheckParam = new Parameter();
     * dateToCheckParam.setName(ParameterName.DATE_TO_CHECK);
     * dateToCheckParam.setValue(dateToCheck);
     * bollingerParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * bollingerRule.setParameters(bollingerParameters);
     * bollingerRule.setOperation(Operation.LINE_CROSS);
     * rules.add(bollingerRule);
     * 
     * // RSI rule Map<String, Parameter> rsiParameters = new HashMap<String,
     * Parameter>(); Rule rsiRule = new Rule(); Parameter rsiSerieParam = new
     * Parameter(); rsiSerieParam.setName(ParameterName.SERIE);
     * rsiSerieParam.setValue(Indicator.RSI);
     * rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);
     * 
     * Parameter rsiOperatorParam = new Parameter();
     * rsiOperatorParam.setName(ParameterName.OPERATION);
     * rsiOperatorParam.setValue(Operator.EGREATER_THAN);
     * rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);
     * 
     * Parameter rsiDateParam = new Parameter();
     * rsiDateParam.setName(ParameterName.DATE_TO_CHECK);
     * rsiDateParam.setValue(dateToCheck);
     * rsiParameters.put(ParameterName.DATE_TO_CHECK.toString(), rsiDateParam);
     * 
     * Parameter rsiThresholdParam = new Parameter();
     * rsiThresholdParam.setName(ParameterName.THRESHOLD);
     * rsiThresholdParam.setValue(63D); rsiParameters
     * .put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
     * rsiRule.setParameters(rsiParameters);
     * rsiRule.setOperation(Operation.COMPARE_VALUE); rules.add(rsiRule);
     * 
     * // Stochastyc operation Rule stochastycRule = new Rule(); Map<String,
     * Parameter> stochastycParameters = new HashMap<String, Parameter>();
     * 
     * Parameter kParam = new Parameter(); kParam.setName(ParameterName.SERIE1);
     * kParam.setValue(Indicator.STOCHASTYC_K);
     * stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);
     * 
     * Parameter dParam = new Parameter(); dParam.setName(ParameterName.SERIE2);
     * dParam.setValue(Indicator.STOCHASTYK_D);
     * stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);
     * 
     * Parameter stochDaysBackParam = new Parameter();
     * stochDaysBackParam.setName(ParameterName.DAYS_BACK);
     * stochDaysBackParam.setValue(2);
     * stochastycParameters.put(ParameterName.DAYS_BACK.toString(),
     * stochDaysBackParam);
     * 
     * Parameter stochDirectionParam = new Parameter();
     * stochDirectionParam.setName(ParameterName.DIRECTION);
     * stochDirectionParam.setValue(CrossDirection.DOWN);
     * stochastycParameters.put(ParameterName.DIRECTION.toString(),
     * stochDirectionParam);
     * 
     * stochastycParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * dateToCheckParam);
     * 
     * stochastycRule.setParameters(stochastycParameters);
     * stochastycRule.setOperation(Operation.LINE_CROSS);
     * rules.add(stochastycRule);
     * 
     * // Sell slope test Rule slopeRule = new Rule(); Parameter slopeSerie =
     * new Parameter(); Map<String, Parameter> slopeParameters = new
     * HashMap<String, Parameter>(); slopeSerie.setName(ParameterName.SERIE);
     * slopeSerie.setValue(Indicator.CLOSE);
     * slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);
     * 
     * Parameter slopeDateParam = new Parameter();
     * slopeDateParam.setName(ParameterName.DATE_TO_CHECK);
     * slopeDateParam.setValue(dateToCheck);
     * slopeParameters.put(ParameterName.DATE_TO_CHECK.toString(),
     * slopeDateParam); slopeRule.setOperation(Operation.SELL_SLOPE_TEST);
     * slopeRule.setParameters(slopeParameters);
     * 
     * rules.add(slopeRule);
     * 
     * RuleSet ruleSet = new RuleSet(); ruleSet.setName("Sell full test");
     * ruleSet.setRules(rules); boolean result = analyzer.analyze(ruleSet,
     * dateToCheck); Assert.assertTrue("MCD debería ser vendida el 14/05/2014 ",
     * result); }
     * 
     * public static void main(final String[] args) throws
     * UnsupportedEncodingException, MalformedURLException,
     * ParserConfigurationException, SAXException, IOException, ParseException {
     * 
     * SymbolAnalyzerTest sat = new SymbolAnalyzerTest(); sat.initObjects();
     * sat.axpAnalyzeTest(); }
     */
}
