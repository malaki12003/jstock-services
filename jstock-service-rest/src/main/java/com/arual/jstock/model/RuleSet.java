package com.arual.jstock.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleSet extends AbstractDTO implements Serializable {

	/** Serial version id. */
	private static final long serialVersionUID = 1L;
	/** Name of the ruleset. */
	private String name;
	/** Description of the ruleset. */
	private String description;
	/** Result of the rule. */
	private Boolean result;

	/** Rule set. */
	private List<Rule> rules;

	/**
	 * Get the rules.
	 * 
	 * @return
	 */
	public List<Rule> getRules() {
		if(this.rules == null){
			this.rules = new ArrayList<Rule>();
		}
		return rules;
	}

	/**
	 * Set a new value for the rules.
	 * 
	 * @param rules
	 */
	public void setRules(List<Rule> rules) {
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
}
