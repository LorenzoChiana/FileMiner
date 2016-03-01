package fileminer.model;

import java.io.IOException;

import javax.swing.JTable;
import javax.swing.tree.TreePath;

/**
 * @author Daniele Gambaletta
 * Interface to manage files and directories table.
 */
public interface FilesTable {

	
	/**
	 * Add new row in the table.
	 * @param node Node
	 * @param nodePath TreePath
	 * @param fst FileSystemTreeImpl
	 * @throws IOException
	 */
	void addRow(Node node, TreePath nodePath, FileSystemTreeImpl fst) throws IOException;
	
	/**
	 * Remove all rows in table.
	 */
	void removeRows();
	
	/**
	 * Get the sum of all column widths.
	 * @param table JTable
	 * @return int dimension
	 */
	int setColumnWidths(final JTable table);
	
	
	
}
