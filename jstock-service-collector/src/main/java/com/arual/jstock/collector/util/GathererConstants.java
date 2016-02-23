/**
 * 
 */
package com.arual.jstock.collector.util;


/**
 * Constants used for gathering.
 * 
 * @author Pablo
 * 
 */
public interface GathererConstants {
	/** Yahoo finance yql api url. */
	String YAHOO_FINANCE_YQL_API_URL = "https://query.yahooapis.com/v1/public/yql";
	/** Query parameter name. */
	String YAHOO_FINANCE_QUERY_PARAMETER_NAME = "q";
	/** Additional parameters. */
	String YAHOO_FINANCE_ADITIONAL_QUERY_PARAMETERS = "diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

	String YAHOO_FORMAT_JSON = "&format=json";
	/** Historical data table. */
	String HISTORICAL_DATA_TABLE = "yahoo.finance.historicaldata";
	/** Xml response quote tag name */
	String QUOTE_TAG_NAME = "quote";
	/** Xml response Closing price tag name */
	String CLOSE_TAG_NAME = "Close";
	/** Xml response date tag name */
	String DATE_TAG_NAME = "Date";
	/** Xml response Opening price tag name */
	String OPEN_TAG_NAME = "Open";
	/** Xml response Highest price tag name */
	String HIGH_TAG_NAME = "High";
	/** Xml response Lowest price tag name */
	String LOW_TAG_NAME = "Low";
	/** Xml response volume tag name */
	String VOLUME_TAG_NAME = "Volume";
	/** Xml response adjusted close tag name */
	String ADJUSTED_CLOSE_TAG_NAME = "Adj_Close";
	/** Xml response symbol attribute name */
	String SYMBOL_ATTRIBUTE_NAME = "Symbol";
	/** Date format pattern in xml. */
	String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	/** Url encoding charset. */
	String URL_ENCODING_CHARSET = "ASCII";

	/** OLD METHOD DATA. */
	/** Daily */
	String QUOTES_SOURCE_DOMAIN_DAILY = "http://finance.yahoo.com";
	/** path **/
	String QUOTES_SOURCE_PATH_DAILY = "/d/quotes.csv";
	String PARAM_SYMBOL = "s";

	String PARAM_BEGIN_MONTH = "a";

	String PARAM_BEGIN_DAY = "b";

	String PARAM_BEGIN_YEAR = "c";

	String PARAM_END_MONTH = "d";

	String PARAM_END_DAY = "e";

	String PARAM_END_YEAR = "f";
	String DAILY_PARAMS_NAME = "f";
	String DAILY_PARAMS = "svghl1od1";

	/** Separator in csv files **/
	String DATA_SEPARATOR = ",";
	
	/**
	 * Hola joder.
	 * @author Pablo
	 *
	 */
	enum Prueba {
		AD,
		BD
	}

}
