package com.arual.jstock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arual.jstock.analyzer.AnalyzerService;
import com.arual.jstock.analyzer.model.RuleResult;
import com.arual.jstock.model.AnalisysResult;
import com.arual.jstock.model.Parameter;
import com.arual.jstock.model.Rule;
import com.arual.jstock.model.RuleSet;
import com.arual.jstock.model.request.AnalyzeRequest;

@RestController
public class AnalyzerController {
//	@Autowired
//	private AnalyzerService analyzerService;

	@RequestMapping(value = "/analyze", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public AnalisysResult analyzeSymbols(@RequestBody final AnalyzeRequest analyzeRequest) {
		AnalisysResult result = new AnalisysResult();
		List<RuleSet> resultsList = new ArrayList<RuleSet>();
		AnalyzerService analyzerService = new AnalyzerService(analyzeRequest.getStartDate(), new HashSet<String>(analyzeRequest.getSymbols()));
		com.arual.jstock.analyzer.model.AnalisysResult resultIntermediate = analyzerService.checkStrictBuyConditionsForStocks(analyzeRequest.getDate2Check());
		
		
		for(String symbol:analyzeRequest.getSymbols()){
			com.arual.jstock.analyzer.model.RuleSet<RuleResult> symbolRes = resultIntermediate.getResultBySymbol(symbol);
			RuleSet ruleForSymbol = new RuleSet();
			ruleForSymbol.setDescription(symbolRes.getDescription());
			ruleForSymbol.setName(symbolRes.getName());
			ruleForSymbol.setResult(symbolRes.getResult());
			List<Rule> rules = new ArrayList<Rule>();
			for(com.arual.jstock.analyzer.model.RuleResult ruleIntermediate:symbolRes.getRules()){
				Rule newRule = new Rule();
				newRule.setDescription(ruleIntermediate.getDescription());
				newRule.setName(ruleIntermediate.getDescription());
				newRule.setOperation(ruleIntermediate.getOperation().toString());
				if(ruleIntermediate.getOperator() != null){
					newRule.setOperator(ruleIntermediate.getOperator().toString());
				}
				
				newRule.setParameters(new HashMap<String, Parameter>());
				if(ruleIntermediate.getParameters()!=null){
					for(String keyParam:ruleIntermediate.getParameters().keySet()){
						Parameter param = new Parameter();
						param.setName(ruleIntermediate.getParameters().get(keyParam).getName().toString());
						param.setValue(ruleIntermediate.getParameters().get(keyParam).getValue().toString());
						newRule.getParameters().put(keyParam, param);			
					}
				}
				rules.add(newRule);
			}
			ruleForSymbol.setRules(rules);
			resultsList.add(ruleForSymbol);
		}
		result.setSymbols(resultsList);
		return result;
	}

	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = { "application/json" })
	public String hello(@PathVariable final String name) {

		return "Hola" + name;
	}
}
