/**
 * 
 */
package com.arual.jstock.collector.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo that represents a quote value for certain date.
 * 
 * @author Pablo
 * 
 */
public class Annotation implements Serializable, Comparable<Annotation> {
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

	public Annotation() {
		super();
	}

	private Annotation(AnnotationBuilder builder) {
		this.adjustedClose = builder.adjustedClose;
		this.close = builder.close;
		this.date = builder.date;
		this.high = builder.high;
		this.low = builder.low;
		this.open = builder.open;
		this.volume = builder.volume;
	}

	/**
	 * Get the date value.
	 * 
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Set a new value for the date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(final LocalDate date) {
		this.date = date;
	}

	/**
	 * Get the opening price.
	 * 
	 * @return the open
	 */
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

	public static class AnnotationBuilder {
		/** Registering date. */
		LocalDate date;
		/** Opening price. */
		Double open;
		/** Highest price of the day. */
		Double high;
		/** Lowest price if the day. */
		Double low;
		/** Closing price. */
		Double close;
		/** Volume. */
		Double volume;
		/** Adjusted closing price. */
		Double adjustedClose;

		public AnnotationBuilder date(LocalDate date) {
			this.date = date;
			return this;
		}

		public AnnotationBuilder open(Double open) {
			this.open = open;
			return this;
		}

		public AnnotationBuilder high(Double high) {
			this.high = high;
			return this;
		}

		public AnnotationBuilder low(Double low) {
			this.low = low;
			return this;
		}

		public AnnotationBuilder close(Double close) {
			this.close = close;
			return this;
		}

		public AnnotationBuilder volume(Double volume) {
			this.volume = volume;
			return this;
		}

		public AnnotationBuilder adjustedClose(Double adjustedClose) {
			this.adjustedClose = adjustedClose;
			return this;
		}

		public Annotation build() {
			return new Annotation(this);
		}

	}

	@Override
	public int compareTo(Annotation o) {
		return this.getDate().compareTo(o.date);
	}
}
