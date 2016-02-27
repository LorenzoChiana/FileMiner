package fileminer.listeners;

import java.util.EventListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;

import fileminer.main.FileMinerLogger;
import fileminer.model.Node;
import fileminer.view.FileMinerGUI;

public class TableNodeSelectionListener implements ListSelectionListener {

    private int rowCount;
    
    private final FileMinerGUI frame;
     
    private final JTable table;
 
	public TableNodeSelectionListener(final FileMinerGUI g, final JTable jt) {
		this.frame = g;
		this.table = jt;
	}
 
    public int getRowCount() {
        return rowCount;
    }
 
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			final ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			int row = lsm.getMinSelectionIndex();

			if ((row >= 0) && (row <= rowCount)) {
				row = table.convertRowIndexToModel(row);
				final boolean selected = (boolean) table.getModel().getValueAt(row, 0);
				final Node fileNode = (Node) table.getModel().getValueAt(row, 2);
				final String path = fileNode.getFilePath();
				final List<String> list = frame.getSelectedItems();

				if (selected && !list.contains(fileNode.getFilePath())) {
					list.add(path);
					FileMinerLogger.getInstance().getConsole().putString(list.size() + " node/s selected");
				} else if (!selected && list.contains(fileNode.getFilePath())) {
					list.remove(path);
					FileMinerLogger.getInstance().getConsole().putString(list.size() + " node/s selected");
				}
			}
		}
	}
}
