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

    /**
     * Get the current main JFrame object.
     * @return the main frame
     */
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

    /**
     * Get the command listener.
     * @return CommandInvokeListener object
     */
    CommandInvokeListener getCommandListener();

    /**
     * Add the current directory to chronology.
     * @param path the current dir
     */
    void addPathToChronology(TreePath path);
}
