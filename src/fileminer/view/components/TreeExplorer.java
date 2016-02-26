package fileminer.view.components;

import javax.swing.ActionMap;
import javax.swing.JTree;

import fileminer.cellrenderer.NodeTreeCellRenderer;
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
     */
    public TreeExplorer(final FileSystemTreeImpl fst, final FileMinerGUI gui) {
        tree = new JTree(fst.getTree());
        tree.addTreeWillExpandListener(new TreeNodeExpandListener(fst));
        tree.addTreeSelectionListener(new TreeNodeSelectionListener(fst, gui));
        tree.setCellRenderer(new NodeTreeCellRenderer());
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setActionMap(new ActionMap());
    }

    /**
     * @return the actual tree.
     */
    public JTree getTree() {
        return tree;
    }

}
