/**
 * 
 */
package com.arual.jstock.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * Pojo that represents a rule.
 * 
 * @author Pablo
 * 
 */
public class Rule extends AbstractDTO implements Serializable {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	/** Rule name. */
	private String name;
	/** Rule description . */
	private String description;
	/** Operation to perform. */
	private String operation;
	/** Parameters. */
	private Map<String, Parameter> parameters;
	/** Rule operator . */
	private String operator;

	/**
	 * Get the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Set a new value for the operation.
	 * 
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(final String operation) {
		this.operation = operation;
	}

	/**
	 * Get the parameters.
	 * 
	 * @return the parameters
	 */
	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Set a new value for the parameters.
	 * 
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(final Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(final String operator) {
		this.operator = operator;
	}

}
