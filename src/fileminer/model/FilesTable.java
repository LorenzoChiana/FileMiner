package fileminer.model;

import java.io.IOException;

import javax.swing.JTable;
import javax.swing.tree.TreePath;

//Interfaccia per impostare la tabella dei files e delle directories
/**
 * @author Daniele
 *
 */
public interface FilesTable {

	void addRow(Node node, TreePath nodePath, FileSystemTreeImpl fst) throws IOException;
	
	void removeRows();
	
	int setColumnWidths(final JTable table);
	
	
	
}
