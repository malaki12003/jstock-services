package com.arual.analyzer.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.arual.analyzer.test.dao.FinanceDaoTest;
import com.arual.jstock.collector.model.Annotation;
import com.arual.jstock.collector.model.Annotations;
import com.arual.jstock.collector.util.YahooFinanceQuoteHandler;

/**
 * Provides basic functionality for test classes in analyzer.
 * 
 * @author Pablo
 * 
 */
public abstract class AbstractAnalyzerTest {
    /** Prices for each day. */
    private Map<String, Annotations> annotationsBySymbol;

    /* Symbols used for tests. */
    public enum TestSymbol {
	AXP("/data/axp.xml","AXP"), BA("/data/ba.xml","BA"), MCD("/data/mcd.xml","MCD"), BMW_DE(
		"/data/bmw.de.xml","BMW.DE"), ADS_DE("/data/ads.de.xml","ADS.DE");

	private String filepath;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	TestSymbol(final String filepath, String code) {
	    this.filepath = filepath;
	    this.code = code;
	}

	/**
	 * Get the filepath
	 * 
	 * @return the filepath
	 */
	public String getFilepath() {
	    return filepath;
	}

	/**
	 * Set a new value for the filepath
	 * 
	 * @param filepath
	 *            the filepath to set
	 */
	public void setFilepath(final String filepath) {
	    this.filepath = filepath;
	}

    }

    /** File containing financial data. */
    public final String FILEPATH = "/data/axp.xml";

    /**
     * Load basic financial info to test with.
     * 
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */

    public void initObjects(final List<TestSymbol> symbols)
	    throws SAXException, IOException, ParserConfigurationException {
	this.annotationsBySymbol = new TreeMap<String, Annotations>();
	for (TestSymbol symbol : symbols) {
	    InputStream is = FinanceDaoTest.class.getResourceAsStream(symbol
		    .getFilepath());
	    YahooFinanceQuoteHandler handler = new YahooFinanceQuoteHandler();
	    SAXParserFactory parserFactor = SAXParserFactory.newInstance();
	    SAXParser parser = parserFactor.newSAXParser();
	    parser.parse(new InputSource(is), handler);

	    for (Annotation quote : handler.getQuotesList()) {
	    	if (this.annotationsBySymbol.get(symbol.getCode()) == null) {
	    		Annotations annotations = new Annotations();
	    		annotations.setSymbol(symbol.getCode());
	    		this.annotationsBySymbol.put(symbol.getCode(), annotations);
	    	}
	    	this.annotationsBySymbol.get(symbol.getCode()).addAnnotation(quote);
	    }
	}

    }

    /**
     * Get the annotations.
     * 
     * @return the annotations
     */
    public Map<String, Annotations> getAnnotations() {
		return annotationsBySymbol;
    }

    /**
     * Set a new value for the annotations.
     * 
     * @param annotations
     *            the annotations to set
     */
    public void setAnnotations(
	    final Map<String, Annotations> annotations) {
	this.annotationsBySymbol = annotations;
    }

}
