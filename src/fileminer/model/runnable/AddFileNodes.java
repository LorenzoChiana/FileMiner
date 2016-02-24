package fileminer.model.runnable;

import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FileSystemTreeImpl;

/**
 *
 */
public class AddFileNodes implements Runnable {
	
	private final FileSystemTreeImpl fst;
	private final DefaultMutableTreeNode node;
	
	/**
	 * Initialization.
	 * @param fst FileSystemTreeImpl
	 * @param node DefaultMutableTreeNode
	 */
	public AddFileNodes(final FileSystemTreeImpl fst, final DefaultMutableTreeNode node) {
		this.fst = fst;
		this.node = node;
	}
	
	@Override
	public void run() {
        fst.addGrandChildren(node);
	}

}
