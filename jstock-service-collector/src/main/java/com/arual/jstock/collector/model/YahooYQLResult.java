package com.arual.jstock.collector.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.PROPERTY)
public class YahooYQLResult implements Serializable {
	
	private YahooQueryResult query;

	public YahooQueryResult getQuery() {
		return query;
	}

	public void setQuery(YahooQueryResult query) {
		this.query = query;
	}

	

}
