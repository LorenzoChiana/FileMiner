package fileminer.listeners;

import java.util.List;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;

import fileminer.view.FileMinerGUI;

public class TableNodeSelectionListener implements TableModelListener {

    private final FileMinerGUI frame;
    private final JTable table;
 
	public TableNodeSelectionListener(final FileMinerGUI g, final JTable jt) {
		this.frame = g;
		this.table = jt;
	}

    @Override
    public void tableChanged(final TableModelEvent e) {
        final int row = e.getFirstRow();
        final TableModel tableModel = (TableModel) e.getSource();

        if (tableModel.getRowCount() > 0) {
            final Boolean selected = (Boolean) tableModel.getValueAt(row, 0);
            final TreePath path = (TreePath) table.getModel().getValueAt(row, 9);
            final List<TreePath> list = frame.getSelectedItems();
    
            if (selected && !list.contains(path)) {
                list.add(path);
            } else if (!selected && list.contains(path)) {
                list.remove(path);
            }
        }
    }
}
