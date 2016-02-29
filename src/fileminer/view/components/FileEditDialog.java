package fileminer.view.components;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class FileEditDialog implements DefaultDialog {

    private final JDialog dialog;

	public FileEditDialog(final JFrame gui) {
		this.dialog = new JDialog(gui, "File edit", true);

	}

	@Override
	public void openDialog() {
		dialog.setVisible(true);
	}
}
