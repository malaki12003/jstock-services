package com.arual.jstock.analyzer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleSet<T extends IRule> implements Serializable {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Name of the ruleset. */
	private String name;
	/** Description of the ruleset. */
	private String description;
	/** Result of the rule. */
	private Boolean result;

	/** Rule set. */
	private List<T> rules;

	/**
	 * Get the rules.
	 * 
	 * @return
	 */
	public List<T> getRules() {
		if(this.rules == null){
			this.rules = new ArrayList<T>();
		}
		return rules;
	}

	/**
	 * Set a new value for the rules.
	 * 
	 * @param rules
	 */
	public void setRules(List<T> rules) {
		this.rules = rules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
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
		RuleSet other = (RuleSet) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rules == null) {
			if (other.rules != null)
				return false;
		} else if (!rules.equals(other.rules))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RuleSet [name=" + name + ", description=" + description
				+ ", rules=" + rules + "]";
	}

	

}
