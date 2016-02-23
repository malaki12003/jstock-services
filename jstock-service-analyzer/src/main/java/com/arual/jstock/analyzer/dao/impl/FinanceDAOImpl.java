/**
 * 
 */
package com.arual.jstock.analyzer.dao.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.dao.IFinanceDataDAO;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.ProfitResult;
import com.arual.jstock.collector.model.Annotation;
import com.arual.jstock.collector.model.Annotations;
import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

/**
 * @author Pablo
 * 
 */
public class FinanceDAOImpl implements IFinanceDataDAO {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateSMA(java.util
     * .List, int)
     */
    @Override
    public List<Double> calculateSMA(final List<Annotation> quotes,
	    final int periodsAverage) {
	double[] out = new double[quotes.size()];
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	RetCode retCode = c.sma(0, closingPrices.length - 1, closingPrices,
		periodsAverage, begin, length, out);
	List<Double> smaList = fromDoubleArray2List(out);
	smaList = adjustDoubleArray(smaList, begin.value);
	return smaList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateSMAWithDate(
     * java.util.List, int)
     */
    @Override
    public Map<LocalDate, Double> calculateSMAWithDate(
	    final List<Annotation> quotes, final int periodsAverage) {
	List<Double> smaList = calculateSMA(quotes, periodsAverage);
	Map<LocalDate, Double> smaByDate = new TreeMap<LocalDate, Double>();
	/* Iterate dates ascending */
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < smaList.size())) {
	    Annotation quote = iterator.next();
	    Double smaValue = smaList.get(index);
	    smaByDate.put(quote.getDate(), smaValue);
	    index++;
	}
	return smaByDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateRSI(java.util
     * .List, int)
     */
    @Override
    public List<Double> calculateRSI(final List<Annotation> quotes,
	    final int periods) {
	double[] out = new double[quotes.size()];
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	RetCode retCode = c.rsi(0, closingPrices.length - 1, closingPrices,
		periods, begin, length, out);

	List<Double> rsiList = fromDoubleArray2List(out);
	
	rsiList = adjustDoubleArray(rsiList, begin.value);
	return rsiList;
    }


    @Override
    public Map<LocalDate, Double> calculateRSIWithDate(
	    final List<Annotation> quotes, final int periods) {
	List<Double> rsiList = calculateRSI(quotes, periods);
	Map<LocalDate, Double> rsiByDate = new TreeMap<LocalDate, Double>();
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < rsiList.size())) {
	    Annotation quote = iterator.next();
	    Double rsiValue = rsiList.get(index);
	    rsiByDate.put(quote.getDate(), rsiValue);
	    index++;
	}
	return rsiByDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateMFI(java.util
     * .List, int)
     */
    @Override
    public List<Double> calculateMFI(final List<Annotation> quotes,
	    final int periods) {
	double[] out = new double[quotes.size()];
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	double[] highPrices = extractHighPriceList(quotes);
	double[] lowPrices = extractLowPriceList(quotes);
	double[] volume = extractVolumeList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	RetCode retCode = c.mfi(0, closingPrices.length - 1, highPrices,
		lowPrices, closingPrices, volume, periods, begin, length, out);

	List<Double> mfiList = fromDoubleArray2List(out);
	mfiList = adjustDoubleArray(mfiList, begin.value);
	return mfiList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateMFIWithDate(
     * java.util.List, int)
     */
    @Override
    public Map<LocalDate, Double> calculateMFIWithDate(
	    final List<Annotation> quotes, final int periods) {
	List<Double> mfiList = calculateMFI(quotes, periods);
	Map<LocalDate, Double> mfiByDate = new TreeMap<LocalDate, Double>();
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < mfiList.size())) {
	    Annotation quote = iterator.next();
	    Double rsiValue = mfiList.get(index);
	    mfiByDate.put(quote.getDate(), rsiValue);
	    index++;
	}
	return mfiByDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateWilliamsR(java
     * .util.List, int)
     */
    @Override
    public List<Double> calculateWilliamsR(final List<Annotation> quotes,
	    final int periods) {
	double[] out = new double[quotes.size()];
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	double[] highPrices = extractHighPriceList(quotes);
	double[] lowPrices = extractLowPriceList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	RetCode retCode = c.willR(0, closingPrices.length - 1, highPrices,
		lowPrices, closingPrices, periods, begin, length, out);
	List<Double> williamRList = fromDoubleArray2List(out);
	williamRList = adjustDoubleArray(williamRList, begin.value);
	return williamRList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.johnredy.arual.analyzer.dao.IFinanceDataDAO#calculateWilliamsRWithDate
     * (java.util.List, int)
     */
    @Override
    public Map<LocalDate, Double> calculateWilliamsRWithDate(
	    final List<Annotation> quotes, final int periods) {
	List<Double> williamsRList = calculateWilliamsR(quotes, periods);
	Map<LocalDate, Double> williamsRByDate = new TreeMap<LocalDate, Double>();
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < williamsRList.size())) {
	    Annotation quote = iterator.next();
	    Double rsiValue = williamsRList.get(index);
	    williamsRByDate.put(quote.getDate(), rsiValue);
	    index++;
	}
	return williamsRByDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.johnredy.arual.analyzer.dao.IFinanceDataDAO#
     * calculateBollingerBandsWithDate(java.util.List, int, int, int)
     */
    @Override
    public Map<Indicator, Map<LocalDate, Double>> calculateBollingerBandsWithDate(
	    final List<Annotation> quotes, final int periods,
	    final int upDevMult, final int downDevMult) {
	double[] outUpperBand = new double[quotes.size()];
	double[] outMiddleBand = new double[quotes.size()];
	double[] outLowerBand = new double[quotes.size()];
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	// FIXME MAType should be parametrized
	RetCode retCode = c.bbands(0, closingPrices.length - 1, closingPrices,
		periods, upDevMult, downDevMult, MAType.Sma, begin, length,
		outUpperBand, outMiddleBand, outLowerBand);
	List<Double> upperBandList = fromDoubleArray2List(outUpperBand);
	List<Double> middleBandList = fromDoubleArray2List(outMiddleBand);
	List<Double> lowerBandList = fromDoubleArray2List(outLowerBand);
	upperBandList = adjustDoubleArray(upperBandList, begin.value);
	middleBandList = adjustDoubleArray(middleBandList, begin.value);
	lowerBandList = adjustDoubleArray(lowerBandList, begin.value);

	Map<LocalDate, Double> upperBandByDate = new TreeMap<LocalDate, Double>();
	Map<LocalDate, Double> middleBandByDate = new TreeMap<LocalDate, Double>();
	Map<LocalDate, Double> lowerBandByDate = new TreeMap<LocalDate, Double>();
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < upperBandList.size())) {
	    Annotation quote = iterator.next();
	    Double upperValue = upperBandList.get(index);
	    Double middleValue = middleBandList.get(index);
	    Double lowerValue = lowerBandList.get(index);

	    upperBandByDate.put(quote.getDate(), upperValue);
	    middleBandByDate.put(quote.getDate(), middleValue);
	    lowerBandByDate.put(quote.getDate(), lowerValue);
	    index++;
	}
	Map<Indicator, Map<LocalDate, Double>> bbandsResult = new HashMap<Indicator, Map<LocalDate, Double>>();
	bbandsResult.put(Indicator.BOLLINGER_HIGH, upperBandByDate);
	bbandsResult.put(Indicator.BOLLINGER_MID, middleBandByDate);
	bbandsResult.put(Indicator.BOLLINGER_LOW, lowerBandByDate);
	return bbandsResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.johnredy.arual.analyzer.dao.IFinanceDataDAO#
     * calculateFastStochastycWithDate(java.util.List, int, int)
     */
    @Override
    public Map<Indicator, Map<LocalDate, Double>> calculateFastStochastycWithDate(
	    final List<Annotation> quotes, final int fastKperiods,
	    final int fastDPeriods) {
	double[] outFastK = new double[quotes.size()];
	double[] outFastD = new double[quotes.size()];

	double[] highPrices = extractHighPriceList(quotes);
	double[] lowPrices = extractLowPriceList(quotes);
	Core c = new Core();
	double[] closingPrices = extractClosingPriceList(quotes);
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	// FIXME MAType should be parametrized
	RetCode retCode = c.stochF(0, closingPrices.length - 1, highPrices,
		lowPrices, closingPrices, fastKperiods, fastDPeriods,
		MAType.Sma, begin, length, outFastK, outFastD);
	List<Double> fastKList = fromDoubleArray2List(outFastK);
	List<Double> fastDList = fromDoubleArray2List(outFastD);

	fastKList = adjustDoubleArray(fastKList, begin.value);
	fastDList = adjustDoubleArray(fastDList, begin.value);

	Map<LocalDate, Double> fastKByDate = new TreeMap<LocalDate, Double>();
	Map<LocalDate, Double> fastDByDate = new TreeMap<LocalDate, Double>();

	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext() && (index < fastKList.size())) {
	    Annotation quote = iterator.next();
	    Double fastKValue = fastKList.get(index);
	    Double fastDValue = fastDList.get(index);
	    fastKByDate.put(quote.getDate(), fastKValue);
	    fastDByDate.put(quote.getDate(), fastDValue);
	    index++;
	}
	Map<Indicator, Map<LocalDate, Double>> stochastycResult = new HashMap<Indicator, Map<LocalDate, Double>>();
	stochastycResult.put(Indicator.STOCHASTYC_K, fastKByDate);
	stochastycResult.put(Indicator.STOCHASTYK_D, fastDByDate);

	return stochastycResult;
    }

    public void printCSVSymbolIndicatorsInfo(
	    final Annotations annotations) {
	Map<LocalDate, Double> sma = calculateSMAWithDate(annotations.getAnnotationList(), 15);

	Map<LocalDate, Double> rsi = calculateRSIWithDate(annotations.getAnnotationList(), 14);

	Map<LocalDate, Double> mfi = calculateMFIWithDate(annotations.getAnnotationList(), 14);

	Map<LocalDate, Double> williamsR = calculateWilliamsRWithDate(annotations.getAnnotationList(), 14);

	Map<Indicator, Map<LocalDate, Double>> bollingerMap = calculateBollingerBandsWithDate(annotations.getAnnotationList(), 20, 2, 2);

	Map<Indicator, Map<LocalDate, Double>> stochastycMap = calculateFastStochastycWithDate(annotations.getAnnotationList(), 5, 3);
	DecimalFormat numberFormat = new DecimalFormat("#.00");
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	System.out
		.println("Date;Close;SMA;RSI;MFI;WILLIAMS;BollingUp;BollingMid;BollingLow;StochK;StochD;Profit;Result;Volume");
	for (LocalDate date : annotations.getAnnotationDates()) {

	    Double closeValue = annotations.getAnnotationByDate(date).getClose();
	    Double smaValue = sma.get(date);
	    Double rsiValue = rsi.get(date);
	    Double mfiValue = mfi.get(date);
	    Double williamsRValue = williamsR.get(date);
	    Double bollingerUValue = bollingerMap.get(Indicator.BOLLINGER_HIGH)
		    .get(date);
	    Double bollingerMValue = bollingerMap.get(Indicator.BOLLINGER_MID)
		    .get(date);
	    Double bollingerLValue = bollingerMap.get(Indicator.BOLLINGER_LOW)
		    .get(date);
	    Double stochK = stochastycMap.get(Indicator.STOCHASTYC_K).get(date);
	    Double stochD = stochastycMap.get(Indicator.STOCHASTYK_D).get(date);
	    Double volume = annotations.getAnnotationByDate(date).getVolume();
	    /* Profit test. */
	    Double profitValue = annotations.getAnnotationByDate(date).getClose()
		    - annotations.getAnnotationByDate(date).getOpen();
	    ProfitResult profit = profitValue > 0 ? ProfitResult.WIN
		    : ProfitResult.LOOSE;

	    if ((closeValue != null) && (smaValue != null)
		    && (rsiValue != null) && (mfiValue != null)
		    && (williamsRValue != null) && (bollingerUValue != null)
		    && (stochK != null)) {
		System.out.println(df.format(date) + ";"
			+ numberFormat.format(closeValue) + ";"
			+ numberFormat.format(smaValue) + ";"
			+ numberFormat.format(rsiValue) + ";"
			+ numberFormat.format(mfiValue) + ";"
			+ numberFormat.format(williamsRValue) + ";"
			+ numberFormat.format(bollingerUValue) + ";"
			+ numberFormat.format(bollingerMValue) + ";"
			+ numberFormat.format(bollingerLValue) + ";"
			+ numberFormat.format(stochK) + ";"
			+ numberFormat.format(stochD) + ";"
			+ numberFormat.format(profitValue) + ";" + profit + ";"
			+ numberFormat.format(volume) + ";");
	    }
	}
    }

    public Double linearRegressionSlope(final List<Annotation> quotes,
	    final int periods) {
	double[] closingPrices = extractClosingPriceList(quotes);
	double[] out = new double[quotes.size()];
	Double slope = null;
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	Core c = new Core();
	RetCode retCode = c.linearRegSlope(0, closingPrices.length - 1,
		closingPrices, periods, begin, length, out);
	List<Double> outAsList = fromDoubleArray2List(out);
	outAsList = adjustDoubleArray(outAsList, begin.value);
	if (!outAsList.isEmpty()) {
	    slope = outAsList.get(outAsList.size() - 1);
	}
	return slope;

    }

    @Override
    public Double linearRegressionSlopeFromDouble(final double[] closingPrices,
	    final int periods) {
	double[] out = new double[closingPrices.length];
	Double slope = null;
	MInteger begin = new MInteger();
	MInteger length = new MInteger();
	Core c = new Core();
	RetCode retCode = c.linearRegSlope(0, closingPrices.length - 1,
		closingPrices, periods, begin, length, out);
	List<Double> outAsList = fromDoubleArray2List(out);
	outAsList = adjustDoubleArray(outAsList, begin.value);
	if (!outAsList.isEmpty()) {
	    slope = outAsList.get(outAsList.size() - 1);
	}
	return slope;

    }

    /**
     * Extract the closing prices array from a List of annotations.
     * 
     * @param quotes
     *            list of quotes.
     * @return array of double.
     */
    private static double[] extractClosingPriceList(
	    final List<Annotation> quotes) {

	double[] closingArray = new double[quotes.size()];
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext()) {
	    Annotation quote = iterator.next();
	    closingArray[index] = quote.getClose();
	    index++;
	}
	return closingArray;
    }

    /**
     * Extract the high prices array from a List of annotations.
     * 
     * @param quotes
     *            list of quotes.
     * @return array of double.
     */
    private static double[] extractHighPriceList(final List<Annotation> quotes) {

	double[] highArray = new double[quotes.size()];
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext()) {
	    Annotation quote = iterator.next();
	    highArray[index] = quote.getHigh();
	    index++;
	}
	return highArray;
    }

    /**
     * Extract the low prices array from a List of annotations.
     * 
     * @param quotes
     *            list of quotes.
     * @return array of double.
     */
    private static double[] extractLowPriceList(final List<Annotation> quotes) {
	double[] lowArray = new double[quotes.size()];
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext()) {
	    Annotation quote = iterator.next();
	    lowArray[index] = quote.getLow();
	    index++;
	}
	return lowArray;
    }

    /**
     * Extract the volume array from a List of annotations.
     * 
     * @param quotes
     *            list of quotes.
     * @return array of double.
     */
    private static double[] extractVolumeList(final List<Annotation> quotes) {
	double[] volumeArray = new double[quotes.size()];
	Iterator<Annotation> iterator = quotes.iterator();
	int index = 0;
	while (iterator.hasNext()) {
	    Annotation quote = iterator.next();
	    volumeArray[index] = quote.getVolume();
	    index++;
	}
	return volumeArray;
    }

    /**
     * Convert from array of double to List of Double.
     * 
     * @param doubleArray
     *            array of double.
     * @return List of Double objects.
     */
    private static List<Double> fromDoubleArray2List(final double[] doubleArray) {
	List<Double> doubleArrayList = new ArrayList<Double>();
	for (int i = 0; i < doubleArray.length; i++) {
	    doubleArrayList.add(doubleArray[i]);
	}
	return doubleArrayList;
    }

    /**
     * Adapt ta lib array of double to java.util.List
     * 
     * @param arrayToAdjust
     * @param beginIndex
     * @return
     */
    private static List<Double> adjustDoubleArray(
	    final List<Double> arrayToAdjust, final int beginIndex) {
	List<Double> adjustedArray = arrayToAdjust.subList(0,
		arrayToAdjust.size() - beginIndex);
	List<Double> dummyList = new ArrayList<Double>(
		Arrays.asList(new Double[beginIndex]));
	dummyList.addAll(adjustedArray);
	return dummyList;

    }

   
}
