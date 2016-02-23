package fileminer.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import fileminer.listeners.*;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorerPanel extends JTree {

    private static final long serialVersionUID = -4298146968287193413L;

    /**
     * Constructor of TreeExplorerPanel.
     */
    public TreeExplorerPanel(final DefaultTreeModel root) {
        super(root);

        //addTreeWillExpandListener(new TreeExpandListener());
        //addTreeSelectionListener(new TreeFileSelectionListener());

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
