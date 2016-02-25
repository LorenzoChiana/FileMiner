package fileminer.view.components;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorer {

    private final JTree tree;

    /**
     * Constructor of TreeExplorerPanel.
     * @param treeModel the tree model
     */
    public TreeExplorer(final DefaultTreeModel treeModel) {
        tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
    }

    /**
     * @return the actual tree.
     */
    public JTree getTree() {
        return tree;
    }
}
