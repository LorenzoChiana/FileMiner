package fileminer.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorerPanel extends JTree {

    private static final long serialVersionUID = -4298146968287193413L;

    /**
     * Constructor of TreeExplorerPanel.
     * @param treeRoots 
     */
    public TreeExplorerPanel(final DefaultMutableTreeNode treeRoots) {
        super(treeRoots);
        expandRow(1);
        //setCellRenderer(new DefaultTreeCellRenderer());
        setRootVisible(false);
        setShowsRootHandles(true);
    }

    /**
     * @return the actual tree.
     */
    public JTree getTree() {
        return this;
    }
}
