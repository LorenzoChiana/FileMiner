package fileminer.view.components;

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
