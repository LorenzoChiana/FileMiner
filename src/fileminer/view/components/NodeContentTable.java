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

    private List<String> selectedNodes;

    private int currentRows;

    public NodeContentTable(final FileSystemTreeImpl f, final FileMinerGUI g) {
    	this.fst = f;
    	this.gui = g;
    	this.selectedNodes = new ArrayList<>();
    	this.currentRows = 0;
        createTable();
    }

    private void createTable() {
    	tableModel = new FilesTableModel();

    	table = new JTable(tableModel);
    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	table.setColumnSelectionAllowed(false);
    	table.setAutoCreateRowSorter(true);
    	table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    	final ListSelectionModel lsm = table.getSelectionModel();
    	lsm.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(final ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					final ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					int row = lsm.getMinSelectionIndex();

					if ((row >= 0) && (row <= currentRows)) {
						row = table.convertRowIndexToModel(row);
						final Node fileNode = (Node) table.getModel().getValueAt(row, 10);
						final String path = fileNode.getFilePath();

						if (!selectedNodes.contains(path)) {
							selectedNodes.add(path);
						} else {
							selectedNodes.remove(path);
						}
					}
				}
			}
		});
    }

    public JTable getTable() {
        return table;
    }

    public List<String> getSelectedNodes() {
    	return selectedNodes;
    }

    public void generateTableByNode(final DefaultMutableTreeNode node) {
        tableModel.removeRows();

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
        this.currentRows = tableModel.getRowCount();
        tableModel.fireTableDataChanged();
    }
}
