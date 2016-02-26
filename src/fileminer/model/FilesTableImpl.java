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
public class FilesTableImpl extends AbstractTableModel implements FilesTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columns = { "Icon", "Name", "Last Modified", "Size", "Hidden", "Writable", "Readable", "Executable", "Edit" };

	private List<List<Object>> rows;

	/**
	 * 
	 */
	public FilesTableImpl() {
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
	public Object getValueAt(final int row, final int column) {
		return this.rows.get(row).get(column);
	}

	@Override
	public void addRow(final Node node, final FileSystemTreeImpl fst, final JButton edit) {
		List<Object> row = new ArrayList<>();

		row.add(node.getFileIcon());
		row.add(node.getFile().getName());
		row.add(node.getFile().lastModified());
		row.add(node.getFile().getTotalSpace());
		row.add(node.getFile().isHidden());
		row.add(node.getFile().canWrite());
		row.add(node.getFile().canRead());
		row.add(node.getFile().canExecute());
		row.add(edit);

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
			return ImageIcon.class;
		case 1: 
			return String.class;
		case 2:
			return Date.class;
		case 3:
			return Long.class;
		case 8:
			return JButton.class;
		default:
			return Boolean.class;
		}
	}
	

	public int setColumnWidths(final JTable table) {
        DefaultTableCellRenderer centerRenderer = 
                new ObjectRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(3)
            .setCellRenderer(centerRenderer);
         
        int width = setColumnWidth(table, 0, 35);
        width += setColumnWidth(table, 1, 200);
        width += setColumnWidth(table, 2, 70);
        width += setColumnWidth(table, 3, 80);
        width += setColumnWidth(table, 4, 0);
        width += setColumnWidth(table, 5, 0);
        width += setColumnWidth(table, 6, 0);
        width += setColumnWidth(table, 7, 0);
        width += setColumnWidth(table, 8, 0);
         
        return width + 30;
    }
     
    private int setColumnWidth(final JTable table, int column, int width) {
        TableColumn tableColumn = table.getColumnModel()
                .getColumn(column);
        JLabel label = new JLabel((String) tableColumn.getHeaderValue());
        Dimension preferred = label.getPreferredSize();
        width = Math.max(width, (int) preferred.getWidth() + 14);
        tableColumn.setPreferredWidth(width);
        tableColumn.setMinWidth(width);
         
        return width;
    }

}
