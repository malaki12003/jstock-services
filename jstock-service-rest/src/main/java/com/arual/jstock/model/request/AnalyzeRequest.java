package com.arual.jstock.model.request;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.arual.jstock.model.AbstractDTO;

public class AnalyzeRequest extends AbstractDTO implements Serializable {
	private static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";
	private LocalDate startDate;
	private LocalDate date2Check;
	private List<String> symbols;
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
	}
	public LocalDate getDate2Check() {
		return date2Check;
	}
	public void setDate2Check(String date2Check) {
		this.date2Check = LocalDate.parse(date2Check, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
	}
	public List<String> getSymbols() {
		return symbols;
	}
	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

}
