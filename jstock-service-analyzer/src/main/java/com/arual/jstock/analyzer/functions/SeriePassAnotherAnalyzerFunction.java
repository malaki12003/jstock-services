/**
 * 
 */
package com.arual.jstock.analyzer.functions;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.model.Parameter;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.CrossDirection;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;

/**
 * Check of a serie is over or under a value.
 * 
 * @author Pablo
 * 
 */
public class SeriePassAnotherAnalyzerFunction extends
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
	Parameter serie1Name = parameters.get(ParameterName.SERIE1.toString());
	if (serie1Name != null) {
	    serie1 = indicators.get(serie1Name.getValue());
	}
	TreeMap<LocalDate, Double> serie2 = null;
	Parameter serie2Name = parameters.get(ParameterName.SERIE2.toString());
	if (serie2Name != null) {
	    serie2 = indicators.get(serie2Name.getValue());
	}
	Parameter crossDirectionParam = parameters.get(ParameterName.DIRECTION
		.toString());
	CrossDirection crossDirection = null;
	if (crossDirectionParam != null) {
	    crossDirection = (CrossDirection) crossDirectionParam.getValue();
	}

	Parameter daysBackParam = parameters.get(ParameterName.DAYS_BACK
		.toString());
	Integer daysBack = null;
	if (daysBackParam != null) {
	    daysBack = (Integer) daysBackParam.getValue();
	}

	if ((serie1 != null) && !serie1.isEmpty() && (serie2 != null)
		&& !serie2.isEmpty() && (crossDirection != null)
		&& (crossDirection != null) && (dateToCheck != null)) {
	    result = ArualAnalyzerFunctions.seriePassAntoherSerie(serie1,
		    serie2, crossDirection, daysBack, dateToCheck);
	}

	return result;
    }

}
