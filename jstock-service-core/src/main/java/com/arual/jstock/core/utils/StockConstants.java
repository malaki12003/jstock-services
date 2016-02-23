/**
 * 
 */
package com.arual.jstock.core.utils;

/**
 * Class to be used as a constants class.
 * 
 * @author Pablo
 *
 */
public final class StockConstants {
	/**
	 * Hidden constructor.
	 */
	private StockConstants() {

	}

	public enum Country {
		SPAIN, EEUU, GERMANY, ITALY, ENGLAND, EUROPE, FRANCE, BRAZIL, JAPAN
	}

	public enum Operator {
		EQUALS, GREATER, LOWER, EGREATER, ELOWER
	}
	
	public enum Conjunction {
		AND, OR
	}
	
	public enum EntityElement {
		INDEX("index"), SYMBOL("symbol");
		
		EntityElement(String value){
			this.value = value;
		}
				
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		
	}

}
