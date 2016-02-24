package fileminer.model.runnable;

import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FileSystemTree;

/**
 *
 */
public class AddFileNodes implements  Runnable {
	
	private FileSystemTree fst;
	private DefaultMutableTreeNode node;
	
	/**
	 * Initialization.
	 * @param fst FileSystemTree
	 * @param node DefaultMutableTreeNode
	 */
	public AddFileNodes(final FileSystemTree fst, final DefaultMutableTreeNode node) {
		this.fst = fst;
		this.node = node;
	}
	
	@Override
	public void run() {
        fst.addGrandChildren(node);
	}

}
