package com.arual.jstock.collector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDate;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.arual.jstock.collector.model.Annotations;

public abstract class DataGatherer {

	/**
	 * Get data for a symbol from a given date.
	 * 
	 * @param symbol
	 *            symbol to query.
	 * @param fromDate
	 *            start date.
	 * @param toDate
	 *            end date.
	 * @return annotations
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public abstract Annotations obtainFinancialDataDaily(String symbol, LocalDate fromDate, LocalDate toDate);
	
	
}
