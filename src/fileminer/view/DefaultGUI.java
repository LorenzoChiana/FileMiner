package fileminer.view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.tree.TreePath;

import fileminer.listeners.CommandInvokeListener;

/**
 * Interface of FileMinerGUI.
 * @author Michele
 *
 */
public interface DefaultGUI {

    JFrame getFrame();

	/**
	 * Return a list of string corresponding to the selected nodes paths.
	 * @return List of String
	 */
	List<TreePath> getSelectedItems();

	/**
	 * Clear selected item path.
	 */
	void clearSelectedItems();

	/**
	 * Set current directory path.
	 * @param path current path
	 */
	void setCurrentDir(TreePath path);

	/**
	 * Return the path of the current directory.
	 * @return String
	 */
	TreePath getCurrentDir();

	/**
	 * @param node 
	 */
	void updateNodesTable(TreePath node);

    CommandInvokeListener getCommandListener();

    void addPathToChronology(TreePath path);
}
