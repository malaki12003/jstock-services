/**
 * 
 */
package com.arual.jstock.core.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.arual.jstock.core.utils.StockConstants.Operator;

/**
 * Pojo to define filters for searhcs.
 * 
 * @author Pablo
 *
 */
@XmlRootElement(name = "filter")
public class Filter implements Serializable {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Field to search. */
	private String field;
	/** Operator. */
	private Operator operator;
	/** Filter value */
	private String value;

	/**
	 * @return the field
	 */
	@XmlElement(name = "field")
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the operator
	 */
	@XmlElement(name = "operator")
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	@XmlElement(name = "value")
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
