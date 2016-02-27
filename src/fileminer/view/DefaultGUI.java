package fileminer.view;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public interface DefaultGUI {

	/**
	 * Return a list of string corresponding to the selected nodes paths.
	 * @return List of String
	 */
	List<String> getSelectedItems();

	/**
	 * Clear selected item path.
	 */
	void clearSelectedItems();

	/**
	 * Set current directory path.
	 * @param path current path
	 */
	void setCurrentDir(String path);

	/**
	 * Return the path of the current directory.
	 * @return String
	 */
	String getCurrentDir();

	/**
	 * @param node
	 */
	void updateNodesTable(DefaultMutableTreeNode node);
	
	/**
	 * 
	 * @return 0 if is a directory
	 *         1 if is a file
	 *         else if the dialog was closed.
	 */
	int newObjectType();
	
	/**
	 * 
	 * @return the name of new object (file, directory or zip)
	 */
	String newObjectName();
}
