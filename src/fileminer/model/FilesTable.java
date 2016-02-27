package fileminer.model;

import javax.swing.JButton;
import javax.swing.JTable;

//Interfaccia per impostare la tabella dei files e delle directories
/**
 * @author Daniele
 *
 */
public interface FilesTable {

	void addRow(Node node, FileSystemTreeImpl fst);
	
	void removeRows();
	
	int setColumnWidths(final JTable table);
	
	
	
}
