package fileminer.cellrenderer;

import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Daniele
 *
 */
public class ObjectRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat formatter;
    
    /**
     * 
     */
    public ObjectRenderer() {
        String pattern = "dd MMM yyyy";
        this.formatter = new SimpleDateFormat(pattern);
    }
     
    @Override
    protected void setValue(final Object value) {
        setText((value == null) ? "" : formatter.format(value));
    }
}
