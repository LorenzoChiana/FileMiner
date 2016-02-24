package fileminer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class NodeContentTable {

    private final JTable nodesTable;

    public NodeContentTable() {
        nodesTable = new JTable();
    }

    public JTable getNodesTable() {
        return nodesTable;
    }

}
