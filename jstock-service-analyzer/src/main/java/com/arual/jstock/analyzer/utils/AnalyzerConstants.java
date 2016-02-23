/**
 * 
 */
package com.arual.jstock.analyzer.utils;

/**
 * Class with constants used.
 * 
 * @author Pablo
 * 
 */
public final class AnalyzerConstants {
	/**
	 * Hidden constructor.
	 */
	private AnalyzerConstants() {
		super();
	}

	/** Bollinger upper band key. */
	public final static String BOLLINGER_UPPER_BAND = "BU";
	/** Bollinger middle band key. */
	public final static String BOLLINGER_MIDDLE_BAND = "BUM";
	/** Bollinger lower band key. */
	public final static String BOLLINGER_LOWER_BAND = "BL";

	/** Stochastyc k band key. */
	public final static String STOCHASTYC_K = "SK";
	/** Stochastyc d band key. */
	public final static String STOCHASTYC_D = "SD";

	public enum ProfitResult {
		WIN, LOOSE
	}

	public final static int RSI_DEFAULT_PERIODS = 14;
	public final static int MFI_DEFAULT_PERIODS = 14;
	/** number of periods. */
	public final static int BOLLINGER_DEFAULT_PERIODS = 20;
	/** M�ltiplo de desviaci�n superior. */
	public final static int BOLLINGER_DEFAULT_UP_DEV_MULT = 2;
	/** M�ltiplo de desviaci�n inferior. */
	public final static int BOLLINGER_DEFAULT_DOWN_DEV_MULT = 2;

	public final static int WILLIAMS_DEFAULT_PERIODS = 14;

	public final static int STOCHASTYC_DEFAULT_FAST_K_PERIODS = 5;
	public final static int STOCHASTYC_DEFAULT_FAST_D_PERIODS = 3;

	public final static int SMA_DEFAULT_PERIODS = 15;

	/**
	 * Operators available.
	 * 
	 * 
	 * 
	 */
	public enum Operator {
		EQUALS, GREATER_THAN, EGREATER_THAN, LOWER_THAN, ELOWER_THAN
	}

	/** Available indicators. */
	public enum Indicator {
		BOLLINGER_HIGH, BOLLINGER_MID, BOLLINGER_LOW, RSI, WILLIAMS, STOCHASTYC_K, STOCHASTYK_D, CLOSE, OPENING, HIGH, LOW, SMA, MFI
	}

	public enum Operation {
		/** Tendency in the last n-days. */
		TENDENCY_LAST_N_DAYS,
		/** If an indicator differs from a value. */
		COMPARE_VALUE,
		/** if a serie cross another */
		LINE_CROSS, BUY_SLOPE_TEST, SELL_SLOPE_TEST
	}

	public enum ParameterName {
		SERIE, SERIE1, SERIE2, OPERATION, THRESHOLD, DIRECTION, DAYS_BACK, DATE_TO_CHECK
	}

	/** Cross direction */
	public enum CrossDirection {
		UP, DOWN
	}

	public enum Slope {
		ASCENDING, DESCENDING
	}

	public enum RuleOperator {
		AND, OR
	}
}
