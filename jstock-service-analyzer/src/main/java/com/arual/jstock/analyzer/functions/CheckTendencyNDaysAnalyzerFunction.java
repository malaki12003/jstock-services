/**
 * 
 */
package com.arual.jstock.analyzer.functions;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operator;

/**
 * Check of a serie is over or under a value.
 * 
 * @author Pablo
 * 
 */
public class CheckTendencyNDaysAnalyzerFunction extends
	AbstractFinancialAnalyzerFunction {

    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /*
     * 
     */
    /**
     * Params required to be passes. <br/>
     * <ul>
     * <li>An operator of type {@link Operator}</li>
     * <li></li>
     * 
     */
    @Override
    public Boolean analyze(final Map<String, Parameter> parameters,
	    final Map<Indicator, TreeMap<LocalDate, Double>> indicators,
	    final LocalDate dateToCheck) {
	TreeMap<LocalDate, Double> serie1 = null;
	boolean result = false;
	// TODO falta por implementar

	return result;
    }

}
