package fileminer.view;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import fileminer.model.FileSystemTree;
import fileminer.model.FileSystemTreeImpl;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorerPanel extends JTree {

    private static final long serialVersionUID = -4298146968287193413L;

    private FileSystemTree roots;

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
