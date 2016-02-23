package com.arual.jstock.analyzer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arual.jstock.analyzer.utils.AnalyzerConstants.CrossDirection;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operation;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;

public class Rules {
	public static RuleSet<Rule> obtainBuyStrictRuleSet() {
		RuleSet<Rule> ruleSet = new RuleSet<Rule>();
		ruleSet.setName("Strict buy ruleset");
		List<Rule> rules = new ArrayList<Rule>();
		// Bollinger params
		Rule bollingerRule = new Rule();
		Map<String, Parameter> bollingerParameters = new HashMap<String, Parameter>();

		Parameter bollSerie1Param = new Parameter();
		bollSerie1Param.setName(ParameterName.SERIE1);
		bollSerie1Param.setValue(Indicator.CLOSE);
		bollingerParameters.put(ParameterName.SERIE1.toString(), bollSerie1Param);

		Parameter bollSerie2Param = new Parameter();
		bollSerie2Param.setName(ParameterName.SERIE2);
		bollSerie2Param.setValue(Indicator.BOLLINGER_LOW);
		bollingerParameters.put(ParameterName.SERIE2.toString(), bollSerie2Param);

		Parameter bollDaysBackParam = new Parameter();
		bollDaysBackParam.setName(ParameterName.DAYS_BACK);
		bollDaysBackParam.setValue(2);
		bollingerParameters.put(ParameterName.DAYS_BACK.toString(), bollDaysBackParam);

		Parameter bollDirectionParam = new Parameter();
		bollDirectionParam.setName(ParameterName.DIRECTION);
		bollDirectionParam.setValue(CrossDirection.UP);
		bollingerParameters.put(ParameterName.DIRECTION.toString(), bollDirectionParam);

		bollingerRule.setName("Bol1 cross up Bol2 in last " + 2 + " days");
		bollingerRule.setParameters(bollingerParameters);
		bollingerRule.setOperation(Operation.LINE_CROSS);
		rules.add(bollingerRule);

		// RSI rule
		Map<String, Parameter> rsiParameters = new HashMap<String, Parameter>();
		Rule rsiRule = new Rule();
		Parameter rsiSerieParam = new Parameter();
		rsiSerieParam.setName(ParameterName.SERIE);
		rsiSerieParam.setValue(Indicator.RSI);
		rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);

		Parameter rsiOperatorParam = new Parameter();
		rsiOperatorParam.setName(ParameterName.OPERATION);
		rsiOperatorParam.setValue(Operator.ELOWER_THAN);
		rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);

		Parameter rsiThresholdParam = new Parameter();
		rsiThresholdParam.setName(ParameterName.THRESHOLD);
		rsiThresholdParam.setValue(39.5);
		rsiParameters.put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
		rsiRule.setName("RSI <= 39.5");
		rsiRule.setParameters(rsiParameters);
		rsiRule.setOperation(Operation.COMPARE_VALUE);
		rules.add(rsiRule);

		// Stochastyc operation
		Rule stochastycRule = new Rule();
		Map<String, Parameter> stochastycParameters = new HashMap<String, Parameter>();

		Parameter kParam = new Parameter();
		kParam.setName(ParameterName.SERIE1);
		kParam.setValue(Indicator.STOCHASTYC_K);
		stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);

		Parameter dParam = new Parameter();
		dParam.setName(ParameterName.SERIE2);
		dParam.setValue(Indicator.STOCHASTYK_D);
		stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);

		Parameter stochDaysBackParam = new Parameter();
		stochDaysBackParam.setName(ParameterName.DAYS_BACK);
		stochDaysBackParam.setValue(2);
		stochastycParameters.put(ParameterName.DAYS_BACK.toString(), stochDaysBackParam);

		Parameter stochDirectionParam = new Parameter();
		stochDirectionParam.setName(ParameterName.DIRECTION);
		stochDirectionParam.setValue(CrossDirection.UP);
		stochastycParameters.put(ParameterName.DIRECTION.toString(), stochDirectionParam);

		stochastycRule.setName("%K cross %D up last 2 days");
		stochastycRule.setParameters(stochastycParameters);
		stochastycRule.setOperation(Operation.LINE_CROSS);
		rules.add(stochastycRule);

		// Buy slope test
		Rule slopeRule = new Rule();
		Parameter slopeSerie = new Parameter();
		Map<String, Parameter> slopeParameters = new HashMap<String, Parameter>();
		slopeSerie.setName(ParameterName.SERIE);
		slopeSerie.setValue(Indicator.CLOSE);
		slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);

		slopeRule.setOperation(Operation.BUY_SLOPE_TEST);
		slopeRule.setParameters(slopeParameters);
		slopeRule.setName("Buy slope for close");

		rules.add(slopeRule);
		ruleSet.setRules(rules);
		return ruleSet;
	}

	public static RuleSet<Rule> obtainBuyLooseRuleSet() {
		RuleSet<Rule> ruleSet = new RuleSet<Rule>();
		ruleSet.setName("Loose buy ruleset");
		List<Rule> rules = new ArrayList<Rule>();

		// RSI rule
		Map<String, Parameter> rsiParameters = new HashMap<String, Parameter>();
		Rule rsiRule = new Rule();
		Parameter rsiSerieParam = new Parameter();
		rsiSerieParam.setName(ParameterName.SERIE);
		rsiSerieParam.setValue(Indicator.RSI);
		rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);

		Parameter rsiOperatorParam = new Parameter();
		rsiOperatorParam.setName(ParameterName.OPERATION);
		rsiOperatorParam.setValue(Operator.ELOWER_THAN);
		rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);

		Parameter rsiThresholdParam = new Parameter();
		rsiThresholdParam.setName(ParameterName.THRESHOLD);
		rsiThresholdParam.setValue(39.5);
		rsiParameters.put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
		rsiRule.setParameters(rsiParameters);
		rsiRule.setOperation(Operation.COMPARE_VALUE);
		rsiRule.setName("RSI <= 39.5");

		rules.add(rsiRule);

		// Stochastyc operation
		Rule stochastycRule = new Rule();
		Map<String, Parameter> stochastycParameters = new HashMap<String, Parameter>();

		Parameter kParam = new Parameter();
		kParam.setName(ParameterName.SERIE1);
		kParam.setValue(Indicator.STOCHASTYC_K);
		stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);

		Parameter dParam = new Parameter();
		dParam.setName(ParameterName.SERIE2);
		dParam.setValue(Indicator.STOCHASTYK_D);
		stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);

		Parameter stochDaysBackParam = new Parameter();
		stochDaysBackParam.setName(ParameterName.DAYS_BACK);
		stochDaysBackParam.setValue(2);
		stochastycParameters.put(ParameterName.DAYS_BACK.toString(), stochDaysBackParam);

		Parameter stochDirectionParam = new Parameter();
		stochDirectionParam.setName(ParameterName.DIRECTION);
		stochDirectionParam.setValue(CrossDirection.UP);
		stochastycParameters.put(ParameterName.DIRECTION.toString(), stochDirectionParam);

		stochastycRule.setParameters(stochastycParameters);
		stochastycRule.setOperation(Operation.LINE_CROSS);
		stochastycRule.setName("%K cross %D up las 2 days");

		rules.add(stochastycRule);

		// Buy slope test
		Rule slopeRule = new Rule();
		Parameter slopeSerie = new Parameter();
		Map<String, Parameter> slopeParameters = new HashMap<String, Parameter>();
		slopeSerie.setName(ParameterName.SERIE);
		slopeSerie.setValue(Indicator.CLOSE);
		slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);

		slopeRule.setOperation(Operation.BUY_SLOPE_TEST);
		slopeRule.setParameters(slopeParameters);
		slopeRule.setName("Buy slope for close");

		rules.add(slopeRule);
		ruleSet.setRules(rules);
		return ruleSet;
	}

	public static RuleSet<Rule> obtainSellStrictRuleSet() {
		RuleSet<Rule> ruleSet = new RuleSet<Rule>();
		ruleSet.setName("Strict sell ruleset");
		List<Rule> rules = new ArrayList<Rule>();

		// Bollinger params
		Rule bollingerRule = new Rule();
		Map<String, Parameter> bollingerParameters = new HashMap<String, Parameter>();

		Parameter bollSerie1Param = new Parameter();
		bollSerie1Param.setName(ParameterName.SERIE1);
		bollSerie1Param.setValue(Indicator.CLOSE);
		bollingerParameters.put(ParameterName.SERIE1.toString(), bollSerie1Param);

		Parameter bollSerie2Param = new Parameter();
		bollSerie2Param.setName(ParameterName.SERIE2);
		bollSerie2Param.setValue(Indicator.BOLLINGER_HIGH);
		bollingerParameters.put(ParameterName.SERIE2.toString(), bollSerie2Param);

		Parameter bollDaysBackParam = new Parameter();
		bollDaysBackParam.setName(ParameterName.DAYS_BACK);
		bollDaysBackParam.setValue(1);
		bollingerParameters.put(ParameterName.DAYS_BACK.toString(), bollDaysBackParam);

		Parameter bollDirectionParam = new Parameter();
		bollDirectionParam.setName(ParameterName.DIRECTION);
		bollDirectionParam.setValue(CrossDirection.DOWN);
		bollingerParameters.put(ParameterName.DIRECTION.toString(), bollDirectionParam);
		bollingerRule.setName("Close cross DOWN boll Inf last 1 day");
		bollingerRule.setParameters(bollingerParameters);
		bollingerRule.setOperation(Operation.LINE_CROSS);
		rules.add(bollingerRule);

		// RSI rule
		Map<String, Parameter> rsiParameters = new HashMap<String, Parameter>();
		Rule rsiRule = new Rule();
		Parameter rsiSerieParam = new Parameter();
		rsiSerieParam.setName(ParameterName.SERIE);
		rsiSerieParam.setValue(Indicator.RSI);
		rsiParameters.put(ParameterName.SERIE.toString(), rsiSerieParam);

		Parameter rsiOperatorParam = new Parameter();
		rsiOperatorParam.setName(ParameterName.OPERATION);
		rsiOperatorParam.setValue(Operator.EGREATER_THAN);
		rsiParameters.put(ParameterName.OPERATION.toString(), rsiOperatorParam);

		Parameter rsiThresholdParam = new Parameter();
		rsiThresholdParam.setName(ParameterName.THRESHOLD);
		rsiThresholdParam.setValue(63D);
		rsiParameters.put(ParameterName.THRESHOLD.toString(), rsiThresholdParam);
		rsiRule.setParameters(rsiParameters);
		rsiRule.setOperation(Operation.COMPARE_VALUE);
		rsiRule.setName("RSI => 63");
		rules.add(rsiRule);

		// Stochastyc operation
		Rule stochastycRule = new Rule();
		Map<String, Parameter> stochastycParameters = new HashMap<String, Parameter>();

		Parameter kParam = new Parameter();
		kParam.setName(ParameterName.SERIE1);
		kParam.setValue(Indicator.STOCHASTYC_K);
		stochastycParameters.put(ParameterName.SERIE1.toString(), kParam);

		Parameter dParam = new Parameter();
		dParam.setName(ParameterName.SERIE2);
		dParam.setValue(Indicator.STOCHASTYK_D);
		stochastycParameters.put(ParameterName.SERIE2.toString(), dParam);

		Parameter stochDaysBackParam = new Parameter();
		stochDaysBackParam.setName(ParameterName.DAYS_BACK);
		stochDaysBackParam.setValue(2);
		stochastycParameters.put(ParameterName.DAYS_BACK.toString(), stochDaysBackParam);

		Parameter stochDirectionParam = new Parameter();
		stochDirectionParam.setName(ParameterName.DIRECTION);
		stochDirectionParam.setValue(CrossDirection.DOWN);
		stochastycParameters.put(ParameterName.DIRECTION.toString(), stochDirectionParam);

		stochastycRule.setParameters(stochastycParameters);
		stochastycRule.setOperation(Operation.LINE_CROSS);
		stochastycRule.setName("%K cross %D DOWN ast 2 days");
		rules.add(stochastycRule);

		// Sell slope test
		Rule slopeRule = new Rule();
		Parameter slopeSerie = new Parameter();
		Map<String, Parameter> slopeParameters = new HashMap<String, Parameter>();
		slopeSerie.setName(ParameterName.SERIE);
		slopeSerie.setValue(Indicator.CLOSE);
		slopeParameters.put(ParameterName.SERIE.toString(), slopeSerie);
		slopeRule.setOperation(Operation.SELL_SLOPE_TEST);
		slopeRule.setParameters(slopeParameters);
		slopeRule.setName("Close slope sell test");

		rules.add(slopeRule);
		ruleSet.setRules(rules);
		return ruleSet;
	}

}
