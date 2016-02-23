/**
 * 
 */
package com.arual.jstock.collector.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Pojo that represents a quote value for certain date.
 * 
 * @author Pablo
 * 
 */

@XmlAccessorType(XmlAccessType.PROPERTY)
public class YahooAnnotation implements Serializable, Comparable<YahooAnnotation> {
	/** Serial version id. */
	private static final long serialVersionUID = 1L;

	/** Registering date. */
	private LocalDate date;
	/** Opening price. */
	private Double open;
	/** Highest price of the day. */
	private Double high;
	/** Lowest price if the day. */
	private Double low;
	/** Closing price. */
	private Double close;
	/** Volume. */
	private Double volume;
	/** Adjusted closing price. */
	private Double adjustedClose;

	/**
	 * Get the date value.
	 * 
	 * @return the date
	 */
	@JsonProperty("Date")
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Set a new value for the date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(final String date) {

		this.date = LocalDate.parse(date);
	}

	/**
	 * Get the opening price.
	 * 
	 * @return the open
	 */
	@JsonProperty("Open")
	public Double getOpen() {
		return open;
	}

	/**
	 * Set a new value for the opening price.
	 * 
	 * @param open
	 *            the open to set
	 */
	public void setOpen(final Double open) {
		this.open = open;
	}

	/**
	 * Get the highest price.
	 * 
	 * @return the high
	 */
	@JsonProperty("High")
	public Double getHigh() {
		return high;
	}

	/**
	 * Set a new value for the highest price.
	 * 
	 * @param high
	 *            the high to set
	 */
	public void setHigh(final Double high) {
		this.high = high;
	}

	/**
	 * Get the lowest price.
	 * 
	 * @return the low
	 */
	@JsonProperty("Low")
	public Double getLow() {
		return low;
	}

	/**
	 * Set a new value for the lowest price.
	 * 
	 * @param low
	 *            the low to set
	 */
	public void setLow(final Double low) {
		this.low = low;
	}

	/**
	 * Get the closing price.
	 * 
	 * @return the close
	 */
	@JsonProperty("Close")
	public Double getClose() {
		return close;
	}

	/**
	 * Set a new value for the closing price.
	 * 
	 * @param close
	 *            the close to set
	 */
	public void setClose(final Double close) {
		this.close = close;
	}

	/**
	 * Get the volume.
	 * 
	 * @return the volume
	 */
	@JsonProperty("Volume")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set a new value for the volume.
	 * 
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(final Double volume) {
		this.volume = volume;
	}

	/**
	 * Get the adjusted closing price.
	 * 
	 * @return the adjustedClose
	 */
	@JsonProperty("Adj_Close")
	public Double getAdjustedClose() {
		return adjustedClose;
	}

	/**
	 * Set a new value for the adjusted closing price.
	 * 
	 * @param adjustedClose
	 *            the adjustedClose to set
	 */
	public void setAdjustedClose(final Double adjustedClose) {
		this.adjustedClose = adjustedClose;
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

	@Override
	public int compareTo(YahooAnnotation o) {
		return this.getDate().compareTo(o.date);
	}
}
