/**
 * 
 */
package com.arual.jstock.analyzer.model;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import com.arual.jstock.analyzer.utils.AnalyzerConstants.Indicator;

/**
 * @author Pablo
 *
 */
public class RuleResult extends Rule {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Result of the operation. */
	private Boolean result;
	/** Parameters. */
	private Map<String, Parameter> parameters;
	/** Indicators . */
	private Map<Indicator, TreeMap<LocalDate, Double>> indicators;
	/** LocalDate to check. */
	private LocalDate dateToCheck;

	/**
	 * Get the result.
	 * 
	 * @return get the result.
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * Set a new value for the result.
	 * 
	 * @param result
	 *            result param.
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}

	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

	public Map<Indicator, TreeMap<LocalDate, Double>> getIndicators() {
		return indicators;
	}

	public void setIndicators(Map<Indicator, TreeMap<LocalDate, Double>> indicators) {
		this.indicators = indicators;
	}

	public LocalDate getDateToCheck() {
		return dateToCheck;
	}

	public void setDateToCheck(LocalDate dateToCheck) {
		this.dateToCheck = dateToCheck;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateToCheck == null) ? 0 : dateToCheck.hashCode());
		result = prime * result
				+ ((indicators == null) ? 0 : indicators.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuleResult other = (RuleResult) obj;
		if (dateToCheck == null) {
			if (other.dateToCheck != null)
				return false;
		} else if (!dateToCheck.equals(other.dateToCheck))
			return false;
		if (indicators == null) {
			if (other.indicators != null)
				return false;
		} else if (!indicators.equals(other.indicators))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RuleResult [result=" + result + ", parameters=" + parameters
				+ ", indicators=" + indicators + ", dateToCheck=" + dateToCheck
				+ "]";
	}

}
