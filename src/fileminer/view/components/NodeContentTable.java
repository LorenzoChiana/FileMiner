package fileminer.view.components;

import javax.swing.JTable;

public class NodeContentTable {

    private final JTable table;

    public NodeContentTable() {
        table = new JTable();
        
    }

    public JTable getNodesTable() {
        return table;
    }
}
