package fileminer.listeners;

import java.io.File;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

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
    private final FileSystemTreeImpl model;
    private final FileMinerGUI view;
    
    /**
     * 
     * @param model
     *          file system tree.
     * @param view
     *          GUI.
     */
    public TreeNodeSelectionListener(final FileSystemTreeImpl model, final FileMinerGUI view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        final Node fileNode = (Node) treeNode.getUserObject();

        final File f = fileNode.getFile();
        if (f.isDirectory()) {
        	final AddFileNodes addFileNodes = new AddFileNodes(this.model, treeNode);
        	new Thread(addFileNodes).start();
        	view.setCurrentDir(f.getAbsolutePath());
        	view.updateNodesTable(treeNode);
        	FileMinerLogger.getInstance().getConsole().putString(view.getCurrentDir());
        } else {
        	view.setCurrentDir(f.getParentFile().getAbsolutePath());
        	view.updateNodesTable(treeNode);
        }
    }
}
