package com.arual.jstock.model;

public class Parameter extends AbstractDTO {
	/** Name of the parameter. */
	private String name;
	/** Value of the parameter. */
	private String value;

	/**
	 * Get the name value.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set a new value for the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set a new value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}