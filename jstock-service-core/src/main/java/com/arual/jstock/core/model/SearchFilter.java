/**
 * 
 */
package com.arual.jstock.core.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.arual.jstock.core.utils.StockConstants.Conjunction;
import com.arual.jstock.core.utils.StockConstants.EntityElement;

/**
 * @author Pablo
 *
 */
@XmlRootElement(name = "searchFilter")
public class SearchFilter implements Serializable {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	private EntityElement element;
	/** Filters to apply. */
	private List<Filter> filters;
	/** Relation between filters. */
	private Conjunction conjunction;

	/**
	 * @return the filters
	 */
	@XmlElementWrapper(name = "filters")
    @XmlElement(name = "filter")
	public List<Filter> getFilters() {
		return filters;
	}

	/**
	 * @param filters
	 *            the filters to set
	 */
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * @return the conjunction
	 */
	@XmlElement(name="conjunction")
	public Conjunction getConjunction() {
		return conjunction;
	}

	/**
	 * @param conjunction
	 *            the conjunction to set
	 */
	public void setConjunction(Conjunction conjunction) {
		this.conjunction = conjunction;
	}

	public EntityElement getElement() {
		return element;
	}

	public void setElement(EntityElement element) {
		this.element = element;
	}

	
}
