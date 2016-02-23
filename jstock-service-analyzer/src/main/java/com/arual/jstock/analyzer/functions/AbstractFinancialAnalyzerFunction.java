/**
 * 
 */
package com.arual.jstock.analyzer.functions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;

/**
 * Abstract class that must e implemented by all analysis
 * 
 * @author Pablo
 * 
 */
public abstract class AbstractFinancialAnalyzerFunction implements Serializable {

    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /**
     * Check the operation for the specified day.
     * 
     * @param parameters
     *            list of params
     * @params indicators Map of indicators
     * @return if the rule is valid for that day.
     */
    public abstract Boolean analyze(final Map<String, Parameter> parameters,
	    final Map<Indicator, TreeMap<LocalDate, Double>> indicators,
	    final LocalDate LocalDateToCheck);

}
