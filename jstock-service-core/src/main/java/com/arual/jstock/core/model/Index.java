package com.arual.jstock.core.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Map;

import com.arual.jstock.core.utils.StockConstants.Country;


public class Index implements Serializable, FinancialElement {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Index code. */
	private String code;
	/** Name. */
	private String name;
	/** Country. */
	private Country country;
	/** Openning time. */
	private LocalTime opening;
	/** Closing time. */
	private LocalTime closing;
	/** Symbols. */
	private Map<String, Symbol> symbols;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public LocalTime getOpening() {
		return opening;
	}
	public void setOpening(LocalTime opening) {
		this.opening = opening;
	}
	public LocalTime getClosing() {
		return closing;
	}
	public void setClosing(LocalTime closing) {
		this.closing = closing;
	}
	public Map<String, Symbol> getSymbols() {
		return symbols;
	}
	public void setSymbols(Map<String, Symbol> symbols) {
		this.symbols = symbols;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closing == null) ? 0 : closing.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((opening == null) ? 0 : opening.hashCode());
		result = prime * result + ((symbols == null) ? 0 : symbols.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Index other = (Index) obj;
		if (closing == null) {
			if (other.closing != null)
				return false;
		} else if (!closing.equals(other.closing))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (country != other.country)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (opening == null) {
			if (other.opening != null)
				return false;
		} else if (!opening.equals(other.opening))
			return false;
		if (symbols == null) {
			if (other.symbols != null)
				return false;
		} else if (!symbols.equals(other.symbols))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Index [code=" + code + ", name=" + name + ", country="
				+ country + ", opening=" + opening + ", closing=" + closing
				+ ", symbols=" + symbols + "]";
	}
	
	

}
