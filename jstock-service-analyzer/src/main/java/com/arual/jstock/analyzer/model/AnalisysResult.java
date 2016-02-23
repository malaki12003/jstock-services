package com.arual.jstock.analyzer.model;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class AnalisysResult implements Serializable {
	
	private ConcurrentHashMap<String, RuleSet<RuleResult>> results;

	public AnalisysResult() {
		results = new ConcurrentHashMap<String, RuleSet<RuleResult>>();
	}
	
	public void addSymbolResult(String symbol, RuleSet<RuleResult> result) {
		results.putIfAbsent(symbol, result);
	}
	
	public RuleSet<RuleResult> getResultBySymbol(String symbol){
		return results.get(symbol);
	}
	
	
	
}
