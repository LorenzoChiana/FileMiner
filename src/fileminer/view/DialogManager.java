package fileminer.view;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class DialogManager {

	private JFrame owner;

	public DialogManager(final JFrame own) {
		this.owner = own;
	}

	public static JDialog getDialog(final Dialogs dialog) {
		switch (dialog) {
		case FILE_EDIT:
			// return new FileEditDialog()
			break;

		case NEW_ITEM:
			//return new NewItemDialog()
			break;

		case ZIP:
			//return new ZipDialog()
			break;

		default: break;
		}
		return null;
	}
}
