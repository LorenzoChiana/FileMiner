package fileminer.listeners;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fileminer.view.FileMinerGUI;

public class TableNodeSelectionListener implements ListSelectionListener {

    private int rowCount;
    
    private FileMinerGUI frame;
     
    private JTable table;
 
	public TableNodeSelectionListener() {
		
	}
 
    public int getRowCount() {
        return rowCount;
    }
 
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }



	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}

}
