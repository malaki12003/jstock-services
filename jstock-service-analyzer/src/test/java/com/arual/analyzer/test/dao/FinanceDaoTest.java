/**
 * 
 */
package com.arual.analyzer.test.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.arual.analyzer.test.AbstractAnalyzerTest;
import com.arual.jstock.analyzer.dao.impl.FinanceDAOImpl;

/**
 * @author Pablo
 * 
 */
public class FinanceDaoTest extends AbstractAnalyzerTest {
	@Before
	public void initObjects() throws SAXException, IOException, ParserConfigurationException {
		List<TestSymbol> symbolsList = new ArrayList<TestSymbol>();
		symbolsList.add(TestSymbol.AXP);
		super.initObjects(symbolsList);
	}

	/**
	 * Basic test of rsi.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testRSI() throws ParseException {
		Assert.assertNotNull(this.getAnnotations());
		FinanceDAOImpl financeDao = new FinanceDAOImpl();
		Map<LocalDate, Double> rsi = financeDao.calculateRSIWithDate(this.getAnnotations().get(TestSymbol.AXP.toString()).getAnnotationList(),
				14);
		Assert.assertNotNull(rsi);
		LocalDate dateToCheck1 = LocalDate.of(2014, Month.MARCH, 26);
		Double valueToCheck1 = rsi.get(dateToCheck1);
		Assert.assertTrue(valueToCheck1.compareTo(46D) < 0);
	}

	public static void main(final String[] args) throws UnsupportedEncodingException, MalformedURLException, ParserConfigurationException,
			SAXException, IOException, ParseException {

		FinanceDaoTest fdt = new FinanceDaoTest();
		fdt.initObjects();
		fdt.testRSI();
	}

}
