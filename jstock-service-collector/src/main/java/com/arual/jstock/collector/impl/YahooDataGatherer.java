/**
 * 
 */
package com.arual.jstock.collector.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.arual.jstock.collector.DataGatherer;
import com.arual.jstock.collector.exception.CollectorRuntimeException;
import com.arual.jstock.collector.exception.NoFinancialDataFoundException;
import com.arual.jstock.collector.model.Annotation;
import com.arual.jstock.collector.model.Annotations;
import com.arual.jstock.collector.model.YahooAnnotation;
import com.arual.jstock.collector.model.YahooQueryResult;
import com.arual.jstock.collector.model.YahooQueryResults;
import com.arual.jstock.collector.model.YahooYQLResult;
import com.arual.jstock.collector.util.GathererConstants;

/**
 * Request financial data.
 * 
 * @author Pablo
 * 
 */
public class YahooDataGatherer extends DataGatherer implements GathererConstants {
	private static final String YAHOO_DATE_FORMAT = "M/d/yyyy";

	final static Logger logger = Logger.getLogger(YahooDataGatherer.class);

	@Override
	public Annotations obtainFinancialDataDaily(final String symbol, final LocalDate fromDate, final LocalDate toDate)
			throws CollectorRuntimeException {
		Annotations annotations = new Annotations();

		try {
			URL url = new URL(buildYahooUri(symbol, fromDate, toDate));
			logger.debug("Trying to get data for symbol:" + symbol);
			annotations = getAnnotationsFromYahooServer(url, symbol);
			if (annotations.isEmpty()) {
				logger.warn("No data found for symbol:" + symbol);
				throw new NoFinancialDataFoundException("No data obtained for " + symbol);
			}
			// Last day test check
			if (annotations.getAnnotationByDate(toDate) == null) {
				Annotation lastAnnotation = extractAnnotationDirectly(symbol);
				if (lastAnnotation != null && toDate.equals(lastAnnotation.getDate())) {
					annotations.addAnnotation(lastAnnotation);
				}
			}
		} catch (IOException | ParseException e) {
			logger.error("Error obtaining data for " + symbol + " " + e);
			throw new CollectorRuntimeException("Error obtaining data for " + symbol + " " + e);
		}

		return annotations;
	}

	private Annotations getAnnotationsFromYahooServer(URL url, String symbol)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		YahooYQLResult queryResultForSymbol = mapper.readValue(url, YahooYQLResult.class);
		return extractAnnotationsFromYahooQueryResult(queryResultForSymbol, symbol);
	}

	public Annotations extractAnnotationsFromYahooQueryResult(YahooYQLResult queryResultForSymbol, String symbol) {
		Annotations annotations = new Annotations();
		annotations.setSymbol(symbol);
		YahooQueryResult queryResult = queryResultForSymbol.getQuery();
		YahooQueryResults results = queryResult.getResults();
		if (results != null) {
			for (YahooAnnotation ya : results.getQuote()) {
				Annotation annotation = new Annotation.AnnotationBuilder().adjustedClose(ya.getAdjustedClose())
						.close(ya.getClose()).date(ya.getDate()).high(ya.getHigh()).low(ya.getLow()).open(ya.getOpen())
						.volume(ya.getVolume()).build();
				annotations.addAnnotation(annotation);
			}
		}

		return annotations;

	}

	private String buildYahooUri(String symbol, LocalDate fromDate, LocalDate toDate)
			throws UnsupportedEncodingException {
		String yqlSentence = buildYqlSentence(symbol, fromDate, toDate);
		String uri = YAHOO_FINANCE_YQL_API_URL + "?" + YAHOO_FINANCE_QUERY_PARAMETER_NAME + "="
				+ URLEncoder.encode(yqlSentence, URL_ENCODING_CHARSET) + "&" + YAHOO_FINANCE_ADITIONAL_QUERY_PARAMETERS
				+ YAHOO_FORMAT_JSON;
		return uri;
	}

	private String buildYqlSentence(final String symbol, final LocalDate startDate, final LocalDate endDate) {

		StringBuilder sentence = new StringBuilder();

		sentence.append("select * from " + HISTORICAL_DATA_TABLE + " where " + "symbol = '" + symbol + "'");
		if (startDate != null) {
			sentence.append("and startDate = " + "'"
					+ startDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)) + "' ");
		}
		if (endDate != null) {
			sentence.append(
					"and endDate = " + "'" + endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)) + "' ");
		}
		return sentence.toString();
	}

	public Annotation extractAnnotationDirectly(String symbol) throws IOException, ParseException {
		URL url;
		String csvString;
		URLConnection urlConn = null;
		InputStreamReader inStream = null;
		Annotation apunte = new Annotation();
		url = new URL(QUOTES_SOURCE_DOMAIN_DAILY + QUOTES_SOURCE_PATH_DAILY + "?" + PARAM_SYMBOL + "=" + symbol + "&"
				+ DAILY_PARAMS_NAME + "=" + DAILY_PARAMS);

		urlConn = url.openConnection();
		urlConn.setUseCaches(false);
		inStream = new InputStreamReader(urlConn.getInputStream());
		BufferedReader buff = new BufferedReader(inStream);
		csvString = buff.readLine();
		Calendar today = Calendar.getInstance();
		while (csvString != null) {
			StringTokenizer tokenizer = new StringTokenizer(csvString, DATA_SEPARATOR);
			// "AXP",3875818,61.29,62.12,61.41,61.54,"1/20/2016"
			tokenizer.nextToken();// Discard the symbol
			String volumenString = tokenizer.nextToken();
			String precioMinimoString = tokenizer.nextToken();
			String precioMaximoString = tokenizer.nextToken();
			String precioCierreString = tokenizer.nextToken();
			String precioAperturaString = tokenizer.nextToken();
			String fechaString = tokenizer.nextToken().replaceAll("\"", "");

			LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern(YAHOO_DATE_FORMAT));
			apunte.setDate(fecha);
			apunte.setClose(Double.parseDouble(precioCierreString));
			apunte.setOpen(Double.parseDouble(precioAperturaString));
			apunte.setHigh(Double.parseDouble(precioMaximoString));
			apunte.setLow(Double.parseDouble(precioMinimoString));
			apunte.setVolume(Double.parseDouble(volumenString));

			csvString = buff.readLine();
		}
		return apunte;
	}

}