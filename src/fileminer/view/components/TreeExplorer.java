package fileminer.view.components;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import fileminer.cellrenderer.TreeNodeCellRenderer;
import fileminer.listeners.TreeNodeExpandListener;
import fileminer.listeners.TreeNodeSelectionListener;
import fileminer.model.FileSystemTreeImpl;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Michele Durante
 *
 */
public class TreeExplorer {

    private final JTree tree;

    /**
     * Constructor of TreeExplorerPanel.
     * @param fst FileSystemTreeImpl object
     * @param gui main frame
     */
    public TreeExplorer(final FileSystemTreeImpl fst, final FileMinerGUI gui) {
        tree = new JTree(fst.getTree());
        tree.setEditable(false);
        tree.addTreeWillExpandListener(new TreeNodeExpandListener(fst));
        tree.addTreeSelectionListener(new TreeNodeSelectionListener(fst, gui));
        tree.setCellRenderer(new TreeNodeCellRenderer());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
    }

    /**
     * Get the tree.
     * @return JTree object
     */
    public JTree getTree() {
        return tree;
    }
}
