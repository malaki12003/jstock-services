package com.arual.jstock.deprecated.analyzer.manager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.arual.jstock.analyzer.dao.impl.FinanceDAOImpl;
import com.arual.jstock.analyzer.exception.AnalyzerRuntimeException;
import com.arual.jstock.analyzer.functions.AbstractFinancialAnalyzerFunction;
import com.arual.jstock.analyzer.functions.BuySlopeStairValidAnalyzerFunction;
import com.arual.jstock.analyzer.functions.CheckTendencyNDaysAnalyzerFunction;
import com.arual.jstock.analyzer.functions.CompareValueAnalyzerFunction;
import com.arual.jstock.analyzer.functions.SellSlopeStairValidAnalyzerFunction;
import com.arual.jstock.analyzer.functions.SeriePassAnotherAnalyzerFunction;
import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.model.Rule;
import com.arual.jstock.analyzer.model.RuleResult;
import com.arual.jstock.analyzer.model.RuleSet;
import com.arual.jstock.analyzer.utils.AnalyzerConstants;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operation;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.RuleOperator;
import com.arual.jstock.collector.model.Annotation;
import com.arual.jstock.collector.model.Annotations;

/**
 * Manager class to analyze a symbol using rules.
 * 
 * @author Pablo
 * 
 */
public class SymbolAnalyzer implements Serializable, Runnable {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** indicators used. */
	private Map<Indicator, Map<LocalDate, Double>> indicators;
	/** Annotations map. */
	private final Annotations annotations;

	private final LocalDate dateToCheck;

	private List<RuleSet<Rule>> rulesToCheck;

	private final Map<String, Map<String, RuleSet<RuleResult>>> resultsHolder;
	private final String symbol;

	/**
	 * Constructors using fields.
	 * 
	 * @param symbol
	 * @param closingPrices
	 */
	public SymbolAnalyzer(Annotations annotationsMap, final LocalDate dateToCheck,
			final Map<String, Map<String, RuleSet<RuleResult>>> resultsHolder, final List<RuleSet<Rule>> rulesToCheck, final String symbol) {
		this.dateToCheck = dateToCheck;
		this.resultsHolder = resultsHolder;
		this.rulesToCheck = rulesToCheck;
		this.symbol = symbol;
		this.annotations = annotationsMap;
	}

	private RuleSet<RuleResult> analyze(final RuleSet<Rule> ruleSet, final LocalDate dateToCheck) {
		boolean result = true;
		RuleSet<RuleResult> resultRuleSet = new RuleSet<RuleResult>();
		resultRuleSet.setDescription(ruleSet.getDescription());
		resultRuleSet.setName(ruleSet.getName());
		for (Rule rule : ruleSet.getRules()) {
			RuleResult ruleResult = new RuleResult();
			ruleResult.setName(rule.getName());
			ruleResult.setDescription(rule.getDescription());
			Map<Indicator, TreeMap<LocalDate, Double>> indicators = fetchIndicatorsFromParams(rule.getParameters());
			AbstractFinancialAnalyzerFunction operation = getOperationHelper(rule.getOperation());
			Boolean currentOperationResult = operation.analyze(rule.getParameters(), indicators, dateToCheck);
			if (RuleOperator.OR.equals(rule.getOperator())) {
				result = result || currentOperationResult;
			} else {
				result = result && currentOperationResult;
			}
			ruleResult.setDateToCheck(dateToCheck);
			ruleResult.setDescription(rule.getDescription());
			ruleResult.setIndicators(indicators);
			ruleResult.setOperation(rule.getOperation());
			ruleResult.setResult(currentOperationResult);
			resultRuleSet.getRules().add(ruleResult);
		}
		resultRuleSet.setResult(result);
		return resultRuleSet;
	}

	private Map<LocalDate, Double> getIndicator(final Indicator indicatorRequest) {
		Map<LocalDate, Double> indicator = null;
		if (this.indicators == null) {
			this.indicators = new HashMap<Indicator, Map<LocalDate, Double>>();
		}
		if (this.indicators.get(Indicator.CLOSE) == null) {
			Map<LocalDate, Double> closingPrices = new TreeMap<LocalDate, Double>();
			for (Annotation annotation : this.annotations.getAnnotationList()) {
				closingPrices.put(annotation.getDate(), annotation.getClose());
			}
			this.indicators.put(Indicator.CLOSE, closingPrices);
		}

		if (this.indicators.get(indicatorRequest) == null) {
			FinanceDAOImpl financeDao = new FinanceDAOImpl();
			switch (indicatorRequest) {
			// SMA, MFI
			case RSI:
				indicator = financeDao.calculateRSIWithDate(this.annotations.getAnnotationList(), AnalyzerConstants.RSI_DEFAULT_PERIODS);
				this.indicators.put(Indicator.RSI, indicator);
				break;
			case BOLLINGER_HIGH:
			case BOLLINGER_MID:
			case BOLLINGER_LOW:
				Map<Indicator, Map<LocalDate, Double>> bollingers = financeDao.calculateBollingerBandsWithDate(
						this.annotations.getAnnotationList(), AnalyzerConstants.BOLLINGER_DEFAULT_PERIODS,
						AnalyzerConstants.BOLLINGER_DEFAULT_UP_DEV_MULT, AnalyzerConstants.BOLLINGER_DEFAULT_DOWN_DEV_MULT);
				this.indicators.put(Indicator.BOLLINGER_HIGH, bollingers.get(Indicator.BOLLINGER_HIGH));
				this.indicators.put(Indicator.BOLLINGER_MID, bollingers.get(Indicator.BOLLINGER_MID));
				this.indicators.put(Indicator.BOLLINGER_LOW, bollingers.get(Indicator.BOLLINGER_LOW));
				break;
			case WILLIAMS:
				indicator = financeDao.calculateWilliamsRWithDate(this.annotations.getAnnotationList(),
						AnalyzerConstants.WILLIAMS_DEFAULT_PERIODS);
				this.indicators.put(Indicator.WILLIAMS, indicator);
				break;

			case STOCHASTYC_K:
			case STOCHASTYK_D:
				Map<Indicator, Map<LocalDate, Double>> stochastyc = financeDao.calculateFastStochastycWithDate(
						this.annotations.getAnnotationList(), AnalyzerConstants.STOCHASTYC_DEFAULT_FAST_K_PERIODS,
						AnalyzerConstants.STOCHASTYC_DEFAULT_FAST_D_PERIODS);
				this.indicators.put(Indicator.STOCHASTYC_K, stochastyc.get(Indicator.STOCHASTYC_K));
				this.indicators.put(Indicator.STOCHASTYK_D, stochastyc.get(Indicator.STOCHASTYK_D));
				break;
			case SMA:
				indicator = financeDao.calculateSMAWithDate(this.annotations.getAnnotationList(),
						AnalyzerConstants.SMA_DEFAULT_PERIODS);
				this.indicators.put(Indicator.SMA, indicator);
				break;
			case MFI:
				indicator = financeDao.calculateMFIWithDate(this.annotations.getAnnotationList(),
						AnalyzerConstants.MFI_DEFAULT_PERIODS);
				this.indicators.put(Indicator.MFI, indicator);
				break;
			default:
				throw new AnalyzerRuntimeException("Unknown indicator");
			}

		}
		indicator = this.indicators.get(indicatorRequest);
		return indicator;
	}

	private AbstractFinancialAnalyzerFunction getOperationHelper(final Operation operation) {
		AbstractFinancialAnalyzerFunction operationHelper = null;
		switch (operation) {
		case BUY_SLOPE_TEST:
			operationHelper = new BuySlopeStairValidAnalyzerFunction();
			break;
		case COMPARE_VALUE:
			operationHelper = new CompareValueAnalyzerFunction();
			break;
		case LINE_CROSS:
			operationHelper = new SeriePassAnotherAnalyzerFunction();
			break;
		case SELL_SLOPE_TEST:
			operationHelper = new SellSlopeStairValidAnalyzerFunction();
			break;
		case TENDENCY_LAST_N_DAYS:
			operationHelper = new CheckTendencyNDaysAnalyzerFunction();
			break;
		default:
			throw new AnalyzerRuntimeException("Unknown operation");
		}
		return operationHelper;
	}

	private Map<Indicator, TreeMap<LocalDate, Double>> fetchIndicatorsFromParams(final Map<String, Parameter> parameters) {
		Map<Indicator, TreeMap<LocalDate, Double>> indicatorsFetched = new HashMap<Indicator, TreeMap<LocalDate, Double>>();
		if (parameters != null) {
			for (Parameter param : parameters.values()) {
				if (ParameterName.SERIE.equals(param.getName()) || ParameterName.SERIE1.equals(param.getName())
						|| ParameterName.SERIE2.equals(param.getName())) {
					indicatorsFetched.put((Indicator) param.getValue(),
							(TreeMap<LocalDate, Double>) this.getIndicator((Indicator) param.getValue()));
				}
			}
		}
		return indicatorsFetched;
	}

	/**
	 * @return the rulesToCheck
	 */
	public List<RuleSet<Rule>> getRulesToCheck() {
		return rulesToCheck;
	}

	/**
	 * @param rulesToCheck
	 *            the rulesToCheck to set
	 */
	public void setRulesToCheck(final List<RuleSet<Rule>> rulesToCheck) {
		this.rulesToCheck = rulesToCheck;
	}

	@Override
	public void run() {
		Map<String, RuleSet<RuleResult>> resultsMap = new HashMap<String, RuleSet<RuleResult>>();
		for (RuleSet<Rule> ruleSet : this.rulesToCheck) {
			RuleSet<RuleResult> resultado = null;
			resultado = this.analyze(ruleSet, dateToCheck);
			resultsMap.put(ruleSet.getName(), resultado);
		}
		this.resultsHolder.put(this.symbol, new ConcurrentHashMap<>(resultsMap));

	}
}
