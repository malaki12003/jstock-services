/**
 * 
 */
package com.arual.jstock.analyzer.model;

import java.io.Serializable;

import com.arual.jstock.collector.model.Annotations;

/**
 * @author Pablo
 * 
 */
public class SymbolData implements Serializable {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Simbol id. */
	private final String symbol;
	/** values. */
	private final Annotations values;

	/** Constructor. */
	public SymbolData(final String symbol, final Annotations values) {
		super();
		this.symbol = symbol;
		this.values = values;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return the values
	 */
	public Annotations getValues() {
		return values;
	}

}
