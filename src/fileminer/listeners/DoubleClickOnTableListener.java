package fileminer.listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;
import fileminer.model.runnable.AddFileNodes;
import fileminer.view.FileMinerGUI;

/**
 * Double click class listener.
 * @author Michele
 *
 */
public class DoubleClickOnTableListener extends MouseAdapter {

    private final FileSystemTreeImpl fst;
    private final FileMinerGUI view;

    /**
     * Constructor of DoubleClickOnTableListener.
     * @param f file system
     * @param g gui
     */
    public DoubleClickOnTableListener(final FileSystemTreeImpl f, final FileMinerGUI g) {
        this.fst = f;
        this.view = g;
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        final JTable table = (JTable) e.getSource();
        final Point p = e.getPoint();
        final int row = table.rowAtPoint(p);

        if (e.getClickCount() == 2) {
            final TreePath treePath = (TreePath) table.getValueAt(row, 9);
            final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            final Node fileNode = (Node) treeNode.getUserObject();
            
            final File f = fileNode.getFile();
            if (f.isDirectory()) {
                final AddFileNodes addFileNodes = new AddFileNodes(fst, treeNode);
                new Thread(addFileNodes).start();
                view.setCurrentDir(treePath);
                view.addPathToChronology(treePath);
                view.updateNodesTable(treePath);
                FileMinerLogger.getInstance().getConsole().putString("Current path: " + view.getCurrentDir().toString());
            } else {
                
            }
        }
    }
}
