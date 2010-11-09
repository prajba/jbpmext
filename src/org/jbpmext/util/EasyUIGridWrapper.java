/**
 * 
 */
package org.jbpmext.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Make a list usable by jQuery EasyUI datagrid.
 * 
 * @author weiht
 *
 */
@SuppressWarnings("rawtypes")
public class EasyUIGridWrapper {
	private List rows;
	public List getRows() {
		return rows;
	}

	public int getTotal() {
		return total;
	}

	private int total;
	
	/**
	 * Single page grid.
	 * 
	 * @param rows Grid rows.
	 */
	public EasyUIGridWrapper(List rows) {
		if (rows == null)
			this.rows = new ArrayList();
		else
			this.rows = rows;
		this.total = rows.size();
	}
	
	/**
	 * Multiple page grid.
	 * 
	 * @param rows Rows for current page.
	 * @param total Total rows.
	 */
	public EasyUIGridWrapper(List rows, int total) {
		if (rows == null)
			this.rows = new ArrayList();
		else
			this.rows = rows;
		this.total = total;
	}
}
