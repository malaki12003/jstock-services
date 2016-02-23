/**
 * 
 */
package com.arual.jstock.analyzer.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.collector.model.Annotation;

/**
 * @author Pablo
 * 
 */
public interface IFinanceDataDAO {
    /**
     * Calculate Simple Moving Average.
     * 
     * @param quotes
     *            annotations list.
     * @param periodsAverage
     *            periods to calculate the average.
     * @return array of doubles with the sma values.
     */
    List<Double> calculateSMA(List<Annotation> quotes, int periodsAverage);

    /**
     * Calculate Simple Moving Average returning a Map of values indexed by
     * date.
     * 
     * @param quotes
     *            annotations list.
     * @param periodsAverage
     *            periods to calculate the average.
     * @return map with sma values indexed by date.
     */
    Map<LocalDate, Double> calculateSMAWithDate(List<Annotation> quotes,
	    int periodsAverage);

    /**
     * Calculate Relative Strength Index.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            RSI period.
     * @return array of doubles containing the rsi for the supplied values.
     */
    List<Double> calculateRSI(List<Annotation> quotes, int periods);

    /**
     * Calculate Relative Strength Index.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            RSI period.
     * @return map with rsi values indexed by date.
     */
    Map<LocalDate, Double> calculateRSIWithDate(List<Annotation> quotes, int periods);

    /**
     * Calculate Money Flux Index MFI.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            MFI periods.
     * @return array of doubles containing the mfi for the supplied values.
     */
    List<Double> calculateMFI(List<Annotation> quotes, int periods);

    /**
     * Calculate Money Flux Index MFI.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            MFI period.
     * @return map with mfi values indexed by date.
     */
    Map<LocalDate, Double> calculateMFIWithDate(List<Annotation> quotes, int periods);

    /**
     * Calculate Williams %R.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            Williams periods.
     * @return array of doubles containing the williams % r for the supplied
     *         values.
     */
    List<Double> calculateWilliamsR(List<Annotation> quotes, int periods);

    /**
     * Calculate Williams %R.
     * 
     * @param quotes
     *            annotation list.
     * @param periods
     *            MFI period.
     * @return map with mfi values indexed by date.
     */
    Map<LocalDate, Double> calculateWilliamsRWithDate(List<Annotation> quotes,
	    int periods);

    /**
     * Calcultates Band of Bollinger indicator.
     * 
     * @param quotes
     *            list of annotations.
     * @param periods
     *            number of periods.
     * @param upDevMult
     *            Múltiplo de desviación superior.
     * @param downDevMult
     *            Múltiplo de desviación inferior.
     * @return bollinger bands values indexed by date.
     */
    Map<Indicator, Map<LocalDate, Double>> calculateBollingerBandsWithDate(
	    List<Annotation> quotes, int periods, int upDevMult, int downDevMult);

    /**
     * Calculate fast stochastyc indicator.
     * 
     * @param quotes
     *            list of annotations.
     * @param fastKperiods
     *            fast k periods.
     * @param fastDPeriods
     *            fast d periods.
     * @return k and d stochastyc values indexed by date.
     */
    Map<Indicator, Map<LocalDate, Double>> calculateFastStochastycWithDate(
	    List<Annotation> quotes, int fastKperiods, int fastDPeriods);

    /**
     * 
     * @param closingPrices
     * @param periods
     * @return
     */
    Double linearRegressionSlopeFromDouble(double[] closingPrices, int periods);
}
