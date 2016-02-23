/**
 * 
 */
package com.arual.jstock.collector.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo to represent a collection of annotation objects
 * 
 * @author Pablo
 *
 */
public class Annotations implements Serializable {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	private Map<LocalDate, Annotation> annotationMap;

	public Annotations() {
		this.annotationMap = new TreeMap<LocalDate, Annotation>();
	}

	public void addAnnotation(Annotation annotation) {
		this.annotationMap.put(annotation.getDate(), annotation);
	}

	public Annotation getAnnotationByDate(LocalDate date) {
		return this.annotationMap.get(date);
	}

	public List<Annotation> getAnnotationList() {
		return new ArrayList<Annotation>(this.annotationMap.values());
	}

	public List<LocalDate> getAnnotationDates() {
		return new ArrayList<LocalDate>(this.annotationMap.keySet());
	}

	public boolean isEmpty() {
		boolean empty = false;
		if (MapUtils.isEmpty(this.annotationMap)) {
			empty = true;
		}
		return empty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
