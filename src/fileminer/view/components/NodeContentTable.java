package fileminer.view.components;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FilesTableModel;
import fileminer.model.Node;
import fileminer.view.FileMinerGUI;
import fileminer.listeners.TableNodeSelectionListener;
import fileminer.main.FileMinerLogger;
import fileminer.model.FileSystemTreeImpl;

public class NodeContentTable {

	private final FileSystemTreeImpl fst;

	private final FileMinerGUI gui;

    private JTable table;

    private FilesTableModel tableModel;

    private TableNodeSelectionListener tnsl;

    public NodeContentTable(final FileSystemTreeImpl f, final FileMinerGUI g) {
    	this.fst = f;
    	this.gui = g;
        createTable();
    }

    private void createTable() {
    	tableModel = new FilesTableModel();

    	table = new JTable(tableModel);
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	table.setColumnSelectionAllowed(false);
    	table.setAutoCreateRowSorter(true);
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    	tnsl = new TableNodeSelectionListener(gui, table);
    	tnsl.setRowCount(tableModel.getRowCount());

    	final ListSelectionModel lsm = table.getSelectionModel();
    	lsm.addListSelectionListener(tnsl);
    }

    public JTable getTable() {
        return table;
    }

    public void generateTableByNode(final DefaultMutableTreeNode node) {
        tableModel.removeRows();
    	gui.clearSelectedItems();

        final Node n = (Node) node.getUserObject();
        if (n.getFile().isDirectory()) {
        	final Enumeration<?> enumeration = node.children();
        	while (enumeration.hasMoreElements()) {
        		final DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) enumeration.nextElement();
        		final Node childFileNode = (Node) childNode.getUserObject();
        		tableModel.addRow(childFileNode, fst);
        	}
        } else {
        	tableModel.addRow(n, fst);
        }
        tnsl.setRowCount(tableModel.getRowCount());
        tableModel.fireTableDataChanged();
    }
}
