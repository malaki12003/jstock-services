/**
 * 
 */
package com.arual.jstock.analyzer.model;

import java.io.Serializable;

import com.arual.jstock.analyzer.utils.AnalyzerConstants.ParameterName;

/**
 * @author Pablo
 * 
 */
public class Parameter implements Serializable {
    /** Serial version id. */
    private static final long serialVersionUID = 1L;
    /** Name of the parameter. */
    private ParameterName name;
    /** Value of the parameter. */
    private Object value;

    /**
     * Get the name value.
     * 
     * @return the name
     */
    public ParameterName getName() {
	return name;
    }

    /**
     * Set a new value for the name.
     * 
     * @param name
     *            the name to set
     */
    public void setName(final ParameterName name) {
	this.name = name;
    }

    /**
     * Get the value.
     * 
     * @return the value
     */
    public Object getValue() {
	return value;
    }

    /**
     * Set a new value.
     * 
     * @param value
     *            the value to set
     */
    public void setValue(final Object value) {
	this.value = value;
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
	result = (prime * result) + ((name == null) ? 0 : name.hashCode());
	result = (prime * result) + ((value == null) ? 0 : value.hashCode());
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
	Parameter other = (Parameter) obj;
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (value == null) {
	    if (other.value != null) {
		return false;
	    }
	} else if (!value.equals(other.value)) {
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
	return "Parameter [name=" + name + ", value=" + value + "]";
    }

}
