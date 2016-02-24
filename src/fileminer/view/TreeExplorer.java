package fileminer.view;

import javax.swing.JTree;

import fileminer.listeners.TreeExpandListener;
import fileminer.listeners.TreeNodeSelectionListener;
import fileminer.model.FileSystemTreeImpl;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorer {

    private final JTree tree;

    /**
     * Constructor of TreeExplorerPanel.
     * @param fst the file system object
     */
    public TreeExplorer(final FileSystemTreeImpl fst) {
        tree = new JTree(fst.getTree());
        tree.addTreeWillExpandListener(new TreeExpandListener());
        tree.addTreeSelectionListener(new TreeNodeSelectionListener());
        tree.setCellRenderer(new NodeTreeCellRenderer(fst));
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
