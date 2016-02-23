/**
 * 
 */
package com.arual.jstock.collector.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.arual.jstock.collector.model.Annotation;

/**
 * Sax quote for yahoo finance yql api.
 * 
 * @author Pablo
 * 
 */
public class YahooFinanceQuoteHandler extends DefaultHandler {
	/** Quotes list obtained from xml. */
	private final List<Annotation> quotesList = new ArrayList<Annotation>();
	/** Current quote. */
	private Annotation quote = null;
	/** Content read from xml. */
	private String content = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	// Triggered when the start of tag is found.
	public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {

		switch (qName) {
		// Create a new Employee object when the start tag is found
		case GathererConstants.QUOTE_TAG_NAME:
			quote = new Annotation();
			
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		switch (qName) {
		case GathererConstants.QUOTE_TAG_NAME:
			quotesList.add(quote);
			break;
		case GathererConstants.DATE_TAG_NAME:

			quote.setDate(LocalDate.parse(content, DateTimeFormatter.ofPattern(GathererConstants.DATE_FORMAT_PATTERN)));

			break;
		case GathererConstants.OPEN_TAG_NAME:
			quote.setOpen(Double.valueOf(content));
			break;
		case GathererConstants.HIGH_TAG_NAME:
			quote.setHigh(Double.valueOf(content));
			break;
		case GathererConstants.LOW_TAG_NAME:
			quote.setLow(Double.valueOf(content));
			break;
		case GathererConstants.CLOSE_TAG_NAME:
			quote.setClose(Double.valueOf(content));
			break;
		case GathererConstants.VOLUME_TAG_NAME:
			quote.setVolume(Double.valueOf(content));
			break;
		case GathererConstants.ADJUSTED_CLOSE_TAG_NAME:
			quote.setAdjustedClose(Double.valueOf(content));
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}

	/**
	 * Get the quotes list.
	 * 
	 * @return the quotesList
	 */
	public List<Annotation> getQuotesList() {
		return quotesList;
	}
}
