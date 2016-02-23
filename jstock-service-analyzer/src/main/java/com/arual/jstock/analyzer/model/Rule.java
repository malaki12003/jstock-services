/**
 * 
 */
package com.arual.jstock.analyzer.model;

import java.io.Serializable;
import java.util.Map;

import com.arual.jstock.analyzer.utils.AnalyzerConstants.Operation;
import com.arual.jstock.analyzer.utils.AnalyzerConstants.RuleOperator;

/**
 * 
 * Pojo that represents a rule.
 * 
 * @author Pablo
 * 
 */
public class Rule implements Serializable, IRule {
    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /** Rule name. */
    private String name;
    /** Rule description . */
    private String description;
    /** Operation to perform. */
    private Operation operation;
    /** Parameters. */
    private Map<String, Parameter> parameters;
    /** Rule operator . */
    private RuleOperator operator;

    /**
     * Get the operation.
     * 
     * @return the operation
     */
    public Operation getOperation() {
	return operation;
    }

    /**
     * Set a new value for the operation.
     * 
     * @param operation
     *            the operation to set
     */
    public void setOperation(final Operation operation) {
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
    public RuleOperator getOperator() {
	return operator;
    }

    /**
     * @param operator
     *            the operator to set
     */
    public void setOperator(final RuleOperator operator) {
	this.operator = operator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result)
		+ ((description == null) ? 0 : description.hashCode());
	result = (prime * result) + ((name == null) ? 0 : name.hashCode());
	result = (prime * result)
		+ ((operation == null) ? 0 : operation.hashCode());
	result = (prime * result)
		+ ((operator == null) ? 0 : operator.hashCode());
	result = (prime * result)
		+ ((parameters == null) ? 0 : parameters.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Rule other = (Rule) obj;
	if (description == null) {
	    if (other.description != null) {
		return false;
	    }
	} else if (!description.equals(other.description)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (operation != other.operation) {
	    return false;
	}
	if (operator != other.operator) {
	    return false;
	}
	if (parameters == null) {
	    if (other.parameters != null) {
		return false;
	    }
	} else if (!parameters.equals(other.parameters)) {
	    return false;
	}
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Rule [name=" + name + ", description=" + description
		+ ", operation=" + operation + ", parameters=" + parameters
		+ ", operator=" + operator + "]";
    }

}
