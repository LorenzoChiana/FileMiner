package fileminer.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import fileminer.model.FileBrowser;
import fileminer.model.FileBrowserImpl;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorerPanel extends JTree {

    private static final long serialVersionUID = -4298146968287193413L;

    private FileBrowser roots;

    public TreeExplorerPanel() {
        super();
        setCellRenderer(new DefaultTreeCellRenderer());
        expandRow(1);
        setRootVisible(false);
        //setShowsRootHandles(true);
    }

    public JTree getTree() {
        return this;
    }
}
