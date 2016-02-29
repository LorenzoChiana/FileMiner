package fileminer.view;

import javax.swing.JFrame;

import fileminer.view.components.DefaultDialog;
import fileminer.view.components.FileEditDialog;
import fileminer.view.components.NewItemDialog;

public class DialogManager {

	private static JFrame owner;

	public DialogManager(final JFrame own) {
		DialogManager.owner = own;
	}

	public static DefaultDialog getDialog(final Dialogs dialog) {
		switch (dialog) {
		case FILE_EDIT:
			return new FileEditDialog(owner);

		case NEW_ITEM:
			return new NewItemDialog(owner);

		case ZIP:
			//return new ZipDialog()
			break;

		default: break;
		}
		return null;
	}
}
