package fileminer.view.components;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.model.FilesTableModel;
import fileminer.model.Node;
import fileminer.view.FileMinerGUI;
import fileminer.listeners.DoubleClickOnTableListener;
import fileminer.listeners.TableNodeSelectionListener;
import fileminer.model.FileSystemTreeImpl;

public class NodeContentTable {

    private JTable table;

    private FilesTableModel tableModel;

    public NodeContentTable(final FileSystemTreeImpl fst, final FileMinerGUI gui) {

        tableModel = new FilesTableModel();

        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setColumnSelectionAllowed(false);
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.addMouseListener(new DoubleClickOnTableListener(fst, gui));
        table.getModel().addTableModelListener(new TableNodeSelectionListener(gui, table));
    }

    /**
     * @return the actual table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Generate table view by passing a TreePath.
     * @param path 
     */
    public void generateTableByPath(final FileMinerGUI gui, final FileSystemTreeImpl fst, final TreePath path) {
        tableModel.removeRows();
    	gui.clearSelectedItems();

        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        final Node node = (Node) treeNode.getUserObject();

        if (node.getFile().isDirectory()) {
            final Node newSelfNode = new Node(node.getFile());
            newSelfNode.setFileName(".");
            tableModel.addRow(newSelfNode, path, fst);
        }

        final TreePath parentPath = path.getParentPath();

        if (parentPath != null && parentPath.getPathCount() > 1) {
            final DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
            final Node parent = (Node) parentNode.getUserObject();
            final Node newParentNode = new Node(parent.getFile());
            newParentNode.setFileName("..");
            tableModel.addRow(newParentNode, parentPath, fst);
        }

        if (node.getFile().isDirectory()) {
        	@SuppressWarnings("unchecked")
            final Enumeration<DefaultMutableTreeNode> enumeration = treeNode.children();
        	while (enumeration.hasMoreElements()) { 
        		final DefaultMutableTreeNode childNode = enumeration.nextElement();
				final TreePath nodePath = new TreePath(childNode.getPath());
        		final Node childFileNode = (Node) childNode.getUserObject();
        		tableModel.addRow(childFileNode, nodePath, fst);
        	}
        } else {
			final TreePath nodePath = new TreePath(treeNode.getPath());
        	tableModel.addRow(node, nodePath, fst);
        }
        tableModel.fireTableDataChanged();
    }
}
