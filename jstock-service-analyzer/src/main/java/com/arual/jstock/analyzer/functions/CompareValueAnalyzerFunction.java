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
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;

/**
 * Check of a serie is over or under a value.
 * 
 * @author Pablo
 * 
 */
public class CompareValueAnalyzerFunction extends
	AbstractFinancialAnalyzerFunction {

    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.functions.AbstractFinancialAnalyzerFunction
     * #analyze(java.util.Map, java.util.Map, java.util.LocalDate)
     */
    @Override
    public Boolean analyze(final Map<String, Parameter> parameters,
	    final Map<Indicator, TreeMap<LocalDate, Double>> indicators,
	    final LocalDate dateToCheck) {
	Map<LocalDate, Double> serie = null;
	boolean result = false;
	Parameter serieName = parameters.get(ParameterName.SERIE.toString());
	if (serieName != null) {
	    serie = indicators.get(serieName.getValue());
	}
	Parameter operatorParam = parameters.get(ParameterName.OPERATION
		.toString());
	Operator op = null;
	if (operatorParam != null) {
	    op = (Operator) operatorParam.getValue();
	}

	Parameter valueParam = parameters.get(ParameterName.THRESHOLD
		.toString());
	Double value = null;
	if (valueParam != null) {
	    value = (Double) valueParam.getValue();
	}

	if ((serie != null) && !serie.isEmpty() && (op != null)
		&& (value != null)) {
	    result = ArualAnalyzerFunctions.compareValue(serie, op, value,
		    dateToCheck);
	}

	return result;
    }

}
