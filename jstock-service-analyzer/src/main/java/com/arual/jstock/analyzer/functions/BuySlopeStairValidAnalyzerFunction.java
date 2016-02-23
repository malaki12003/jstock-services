package com.arual.jstock.analyzer.functions;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;

/**
 * To test the Bu slope test. Es válido si la seerie está bajando en los últimos
 * 20 días, y en los últimos 5 días es más pronunciada que en los últimos 10.
 * 
 * @author Pablo
 * 
 */
public class BuySlopeStairValidAnalyzerFunction extends
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
	TreeMap<LocalDate, Double> serie1 = null;
	boolean result = false;
	Parameter serieName = parameters.get(ParameterName.SERIE.toString());
	if (serieName != null) {
	    serie1 = indicators.get(serieName.getValue());
	}

	if ((serie1 != null) && !serie1.isEmpty() && (dateToCheck != null)) {
	    Double slope20 = ArualAnalyzerFunctions.calculateSlopeForNDaysBack(
		    serie1, dateToCheck, 20);
	    Double slope10 = ArualAnalyzerFunctions.calculateSlopeForNDaysBack(
		    serie1, dateToCheck, 10);
	    Double slope5 = ArualAnalyzerFunctions.calculateSlopeForNDaysBack(
		    serie1, dateToCheck, 5);

	    if ((slope20 < 0) && (slope10 < 0) && (slope5 < 0)
		    && (slope5 > slope10)) {
		result = true;
	    }

	}

	return result;
    }

}
