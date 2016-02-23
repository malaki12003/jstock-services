package com.arual.jstock.analyzer.functions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.arual.jstock.analyzer.dao.IFinanceDataDAO;
import com.arual.jstock.analyzer.dao.impl.FinanceDAOImpl;
import com.arual.jstock.analyzer.exception.AnalyzerRuntimeException;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.CrossDirection;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Slope;

public class ArualAnalyzerFunctions {
    /** Logger. */
    private final static Logger logger = Logger
	    .getLogger(ArualAnalyzerFunctions.class);

    /**
     * Check a serie compared to a threshold for a given day.
     * 
     * @param serie
     *            serie to check.
     * @param operator
     *            operator to apply.
     * @param threshold
     *            threshold to check.
     * @LocalDate to check
     * @return
     */
    public static boolean compareValue(final Map<LocalDate, Double> serie,
	    final Operator operator, final Double threshold,
	    final LocalDate dateToCheck) {
	boolean result = true;
	if (serie.get(dateToCheck) != null) {
	    switch (operator) {
	    case EQUALS:
		return serie.get(dateToCheck).compareTo(threshold) == 0;
	    case EGREATER_THAN:
		return serie.get(dateToCheck).compareTo(threshold) >= 0;
	    case GREATER_THAN:
		return serie.get(dateToCheck).compareTo(threshold) > 0;
	    case ELOWER_THAN:
		return serie.get(dateToCheck).compareTo(threshold) <= 0;
	    case LOWER_THAN:
		return serie.get(dateToCheck).compareTo(threshold) < 0;
	    }
	} else {
	    logger.trace("[ArualAnalyzerFunctions] Exception: No data for the supplied date "
		    + dateToCheck);
	    throw new AnalyzerRuntimeException("No data for the supplied date");
	}
	return result;
    }

    /**
     * Check if serie 1 cross serie 2 in crossDirection.
     * 
     * @param serie1
     *            first serie
     * @param serie2
     *            second serie
     * @param crossDirection
     *            direction to check
     * @param daysBack
     *            days to look back
     * @param dateToCheck
     *            date to check.
     * @return
     */
    public static boolean seriePassAntoherSerie(
	    final TreeMap<LocalDate, Double> serie1,
	    final TreeMap<LocalDate, Double> serie2,
	    final CrossDirection crossDirection, final int daysBack,
	    final LocalDate dateToCheck) {
	boolean result = true;
	NavigableMap<LocalDate, Double> subSerie1 = serie1
		.headMap(dateToCheck, true);
	NavigableMap<LocalDate, Double> subSerie2 = serie2
		.headMap(dateToCheck, true);

	Object[] serie1Arr = subSerie1.values().toArray();
	Object[] serie2Arr = subSerie2.values().toArray();
	boolean hasCross = false;
	boolean initialConditionOk = false;
	boolean continueOk = true;
	if ((serie1Arr.length > daysBack) && (serie2Arr.length > daysBack)) {

	    /* Para cruzar, debes mirar un día más. */
	    for (int i = (serie1Arr.length - daysBack - 1); i <= (serie1Arr.length - 1); i++) {
		if ((serie1Arr[i] == null) || (serie2Arr[i] == null)) {
		    logger.warn("[ArualAnalyzerFunctions:seriePassAntoherSerie] No data for the supplied date");
		    continue;
		}

		double val1 = (double) serie1Arr[i];
		double val2 = (double) serie2Arr[i];
		switch (crossDirection) {
		case UP:
		    if ((val1 < val2) && !initialConditionOk) {
			initialConditionOk = true;
			break;
		    }
		    if (initialConditionOk && (val1 > val2) && !hasCross) {
			hasCross = true;
			break;
		    }
		    if (initialConditionOk && hasCross && (val1 < val2)) {
			continueOk = false;
		    }
		    break;
		case DOWN:
		    if ((val1 > val2) && !initialConditionOk) {
			initialConditionOk = true;
			break;
		    }
		    if (initialConditionOk && (val1 < val2) && !hasCross) {
			hasCross = true;
			break;
		    }
		    if (initialConditionOk && hasCross && (val1 > val2)) {
			continueOk = false;
		    }
		    break;
		}
	    }
	}

	if (hasCross && initialConditionOk && continueOk) {
	    result = true;
	} else {
	    result = false;
	}
	return result;
    }

    public static boolean checkTendency(final TreeMap<LocalDate, Double> serie,
	    final LocalDate dateTo, final int daysBack, final Slope slope,
	    final double slopeThreshold) {
	boolean result = false;
	double pendiente;
	try {
	    pendiente = calculateSlopeForNDaysBack(serie, dateTo, daysBack);
	    if (Slope.ASCENDING.equals(slope) && (pendiente > 0)
		    && (Math.abs(pendiente) > slopeThreshold)) {
		result = true;
	    } else if (Slope.DESCENDING.equals(slope) && (pendiente < 0)
		    && (Math.abs(pendiente) > slopeThreshold)) {
		result = true;
	    }
	} catch (AnalyzerRuntimeException ex) {
	    return false;
	}
	return result;
    }

    public static double calculateSlopeForNDaysBack(
	    final TreeMap<LocalDate, Double> serie, final LocalDate dateTo,
	    final int daysBack) {
	double slope;
	NavigableMap<LocalDate, Double> subSerie = serie.headMap(dateTo, true);
	if (subSerie.size() > daysBack) {
	    int i = 0;
	    double[] doublesSubArray = new double[subSerie.size()];
	    for (Double val : subSerie.values()) {
		if (val != null) {
		    doublesSubArray[i] = val.doubleValue();
		} else {
		    throw new AnalyzerRuntimeException(
			    "[ArualAnalyzerFunctions.calculateSlopeForNDaysBack] Error in data serie. Null value found");
		}
		i++;
	    }
	    double[] doublesSubArray2 = Arrays.copyOfRange(doublesSubArray,
		    doublesSubArray.length - 1 - daysBack,
		    doublesSubArray.length);
	    IFinanceDataDAO financeDao = new FinanceDAOImpl();
	    slope = financeDao.linearRegressionSlopeFromDouble(
		    doublesSubArray2, doublesSubArray2.length);

	} else {
	    throw new AnalyzerRuntimeException(
		    "[ArualAnalyzerFunctions.calculateSlopeForNDaysBack] Error in data serie. serie size doesn't seize dasyBack");
	}
	return slope;
    }
}
