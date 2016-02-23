package com.arual.jstock.collector.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class YahooQueryResult implements Serializable {
	private YahooQueryResults results;

	@XmlElement(name = "results")
	public YahooQueryResults getResults() {
		return results;
	}

	public void setResults(YahooQueryResults results) {
		this.results = results;
	}

}
