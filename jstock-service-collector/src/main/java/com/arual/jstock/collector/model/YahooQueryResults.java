package com.arual.jstock.collector.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class YahooQueryResults {
	private List<YahooAnnotation> annotations;

	
	@XmlElement(name="quote")
	public List<YahooAnnotation> getQuote() {
		return annotations;
	}

	public void setQuote(List<YahooAnnotation> annotations) {
		this.annotations = annotations;
	}
}
