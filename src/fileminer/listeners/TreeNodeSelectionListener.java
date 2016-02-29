package fileminer.listeners;

import java.io.File;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;
import fileminer.model.runnable.AddFileNodes;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class TreeNodeSelectionListener implements TreeSelectionListener {

    private final FileSystemTreeImpl treeModel;
    private final FileMinerGUI view;
    
    /**
     * 
     * @param model
     *          file system tree.
     * @param v
     *          GUI.
     */
    public TreeNodeSelectionListener(final FileSystemTreeImpl model, final FileMinerGUI v) {
        this.treeModel = model;
        this.view = v;
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final TreePath treePath = e.getPath();
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        final Node fileNode = (Node) treeNode.getUserObject();

        final File f = fileNode.getFile();
        if (f.isDirectory()) {
            final AddFileNodes addFileNodes = new AddFileNodes(this.treeModel, treeNode);
            new Thread(addFileNodes).start();
            view.setCurrentDir(treePath);
            view.addPathToChronology(treePath);
            view.updateNodesTable(treePath);
            FileMinerLogger.getInstance().getConsole().putString("Current path: " + view.getCurrentDir().toString());
        } else {
            view.setCurrentDir(treePath.getParentPath());
            view.addPathToChronology(treePath.getParentPath());
            view.updateNodesTable(new TreePath(treeNode.getPath()));
            FileMinerLogger.getInstance().getConsole().putString("Current path: " + view.getCurrentDir().toString());
        }
    }
}
