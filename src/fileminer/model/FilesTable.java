package fileminer.model;

import javax.swing.JTable;

/**
 * @author Daniele Gambaletta
 * Table management.
 */
public interface FilesTable {

	/**
	 * Add a new row(file) in the table.
	 * @param node node to add
	 * @param fst FileSystemTreeImpl
	 */
	void addRow(Node node, FileSystemTreeImpl fst);
	
	/**
	 * Remove all rows.
	 */
	void removeRows();

	/**
	 * Set width of the entire table.
	 * @param table JTable
	 * @return total width
	 */
	int setColumnWidths(JTable table);
	
	
	
	
}
