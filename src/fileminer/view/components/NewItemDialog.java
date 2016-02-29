package fileminer.view.components;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class NewItemDialog implements DefaultDialog {

	private final JDialog dialog;

    public NewItemDialog(final JFrame gui) {
    	this.dialog = new JDialog(gui, "New item", true);
    }

	@Override
	public void openDialog() {
		dialog.setVisible(true);
	}
}
