package com.arual.jstock.model;

import java.util.List;

public class AnalisysResult extends AbstractDTO {
	private List<RuleSet> symbols;

	public List<RuleSet> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<RuleSet> symbols) {
		this.symbols = symbols;
	}
}
