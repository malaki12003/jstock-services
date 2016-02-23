package com.arual.jstock.analyzer.results;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.arual.jstock.analyzer.model.AnalisysResult;
import com.arual.jstock.analyzer.model.RuleResult;
import com.arual.jstock.analyzer.model.RuleSet;

public class BasicResultHolder {

	private static Map<String, AnalisysResult> resultsMap = new ConcurrentHashMap<String, AnalisysResult>();

	public static RuleSet<RuleResult> getResultsBySumbol(String uuid, String symbol) {
		AnalisysResult analysysResult = resultsMap.get(uuid);
		RuleSet<RuleResult> resultRule = null;
		if (analysysResult != null) {
			resultRule = analysysResult.getResultBySymbol(symbol);
		}
		return resultRule;
	}

	public static void insertResult(String uuid, String symbol, RuleSet<RuleResult> symbolResult) {		
		resultsMap.putIfAbsent(uuid, new AnalisysResult());
		resultsMap.get(uuid).addSymbolResult(symbol, symbolResult);
	}

	public static AnalisysResult getResultsMap(String uuid) {
		return resultsMap.remove(uuid);
	}
	
	

}
