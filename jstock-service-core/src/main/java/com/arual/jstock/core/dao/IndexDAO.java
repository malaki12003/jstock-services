package com.arual.jstock.core.dao;

import java.util.List;

import com.arual.jstock.core.model.FinancialElement;
import com.arual.jstock.core.model.Index;
import com.arual.jstock.core.model.SearchFilter;
import com.arual.jstock.core.model.Symbol;

public interface IndexDAO {
	/**
	 * Creates an index.
	 * 
	 * @param newIndex
	 *            index to create.
	 */
	void createIndex(Index newIndex);

	/**
	 * Delete an index.
	 * 
	 * @param indexCode
	 *            code of the index to delete.
	 */
	void deleteIndex(String indexCode);

	/**
	 * Get an index by its id.
	 * 
	 * @param indexCode
	 *            index to get.
	 * @return the index
	 */
	Index getIndex(String indexCode);

	/**
	 * To create a symbol.
	 * 
	 * @param symbol
	 *            symbol to create.
	 */
	void createSymbol(Symbol symbol);

	/**
	 * Update an index.
	 * 
	 * @param index
	 */
	void updateIndex(Index index);

	/**
	 * DElete a symbol given its code.
	 * 
	 * @param symbolCode
	 */
	void deleteSymbol(String symbolCode);

	/**
	 * Update symbol
	 * 
	 * @param symbol
	 */
	void updateSymbol(Symbol symbol);

	/**
	 * Get symbol
	 * 
	 * @param symbolCode
	 * @return
	 */
	Symbol getSymbol(String symbolCode);
	
	
	List<FinancialElement> search(SearchFilter filter);
	
	
}
