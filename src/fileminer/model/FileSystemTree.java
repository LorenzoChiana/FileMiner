package fileminer.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Daniele Gambaletta
 * Creation of tree from filesystem.
 */
public interface FileSystemTree {


	/**
	 * Create a tree from FileSystem.
	 * @return DefaultMutableTreeNode
	 */
	DefaultTreeModel getTree();

	/**
	 * Add grand children to the node.
	 * @param node tree node
	 */
	void addGrandChildren(DefaultMutableTreeNode node);

	/**
	 * Add children to the node.
	 * @param node tree node
	 */
	void addChildren(DefaultMutableTreeNode node);


}
