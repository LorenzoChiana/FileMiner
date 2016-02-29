package fileminer.view.components;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class FileEditDialog {

    private final JDialog dialog;

	public FileEditDialog(final JFrame gui) {
		this.dialog = new JDialog(gui, "New item", true);

	}
}
