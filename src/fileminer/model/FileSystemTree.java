package fileminer.model;

import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Daniele Gambaletta
 * Interface to manage tree.
 */
public interface FileSystemTree {


	/**
	 * Get filesystem tree.
	 * @return DefaultMutableTreeNode
	 */
	DefaultTreeModel getTree();

	/**
     * Add file to reload tree, after an operation.
     * @param rootNode tree node
     * @param files 
     */
    void addNodesToTree(DefaultMutableTreeNode rootNode, List<File> files);

    /**
     * Move old nodes in new position.
     * @param rootNode tree node
     * @param oldNodes old tree nodes
     */
    void moveNodes(DefaultMutableTreeNode rootNode, List<DefaultMutableTreeNode> oldNodes);

	/**
	 * Reload tree if changed.
	 * @param rootNode tree node
	 */
	void reloadTreeByNode(DefaultMutableTreeNode rootNode);

	/**
	 * Add files grand children in the tree.
	 * @param rootNode tree node
	 */
	void addGrandChildren(DefaultMutableTreeNode rootNode);

	/**
	 * Add files children in the tree.
	 * @param rootNode tree node
	 */
	void addChildren(DefaultMutableTreeNode rootNode);
}
