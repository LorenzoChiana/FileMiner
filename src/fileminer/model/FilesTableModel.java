package fileminer.model;

import java.awt.Dimension;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import fileminer.cellrenderer.ObjectRenderer;



/**
 * Classe per impostare la tabella dei files e delle directories.
 * @author Daniele
 *
 */
public class FilesTableModel extends AbstractTableModel implements FilesTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columns = { "X", "Icon", "Name", "Last Modified", "Size", "Hidden", "Writable", "Readable", "Executable" };

	private List<List<Object>> rows;

	/**
	 * 
	 */
	public FilesTableModel() {
        this.rows = new ArrayList<List<Object>>();
        DateFormat.getDateInstance(DateFormat.DEFAULT);
    }
	
	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public int getRowCount() {
		return this.rows.size();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//super.setValueAt(aValue, rowIndex, columnIndex);
		this.rows.get(rowIndex).set(columnIndex, aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(final int row, final int column) {
		return this.rows.get(row).get(column);
	}

	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

	@Override
	public void addRow(final Node node, final FileSystemTreeImpl fst) {
		List<Object> row = new ArrayList<>();

		row.add(Boolean.valueOf(false));
		row.add(node.getFileIcon());
		row.add(node);
		row.add(node.getFile().lastModified());
		row.add(node.getFile().getTotalSpace());
		row.add(node.getFile().isHidden());
		row.add(node.getFile().canWrite());
		row.add(node.getFile().canRead());
		row.add(node.getFile().canExecute());

		this.rows.add(row);
	}
	
	@Override
	public void removeRows() {
        this.rows = new ArrayList<>();
    }

	@Override
	public String getColumnName(final int column) {
		return columns[column];
	}	

	@Override
	public Class<?> getColumnClass(final int column) {
		switch (column) {
		case 0:
			return Boolean.class;
		case 1:
			return ImageIcon.class;
		case 2: 
			return String.class;
		case 3:
			return Date.class;
		case 4:
			return Long.class;
		case 5:
		case 6:
		case 7:
		case 8:
			return Boolean.class;
		default:
			return null;
		}
	}
	
	public int setColumnWidths(final JTable table) {
        DefaultTableCellRenderer centerRenderer = new ObjectRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
         
        int width = setColumnWidth(table, 0, 20);
        width += setColumnWidth(table, 1, 100);
        width += setColumnWidth(table, 2, 70);
        width += setColumnWidth(table, 3, 80);
        width += setColumnWidth(table, 4, 0);
        width += setColumnWidth(table, 5, 0);
        width += setColumnWidth(table, 6, 0);
        width += setColumnWidth(table, 7, 0);
        width += setColumnWidth(table, 8, 0);
        width += setColumnWidth(table, 9, 0);
         
        return width + 30;
    }
     
    private int setColumnWidth(final JTable table, int column, int width) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        JLabel label = new JLabel((String) tableColumn.getHeaderValue());
        Dimension preferred = label.getPreferredSize();
        width = Math.max(width, (int) preferred.getWidth() + 14);
        tableColumn.setPreferredWidth(width);
        tableColumn.setMinWidth(width);
         
        return width;
    }

}
