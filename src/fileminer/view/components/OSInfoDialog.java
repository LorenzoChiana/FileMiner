package fileminer.view.components;

import javax.swing.JOptionPane;

import fileminer.controller.Controller;

/**
 * OSInfoDialog class.
 * @author Michele
 *
 */
public class OSInfoDialog {

	private final String text;

	/**
	 * Constructor of OSInfoDialog.
	 * @param ctrl controller
	 */
	public OSInfoDialog(final Controller ctrl) {
		text = ctrl.getOSInfo();
	}

	/**
	 * Open dialog.
	 */
	public void openDialog() {
		JOptionPane.showMessageDialog(null, text, "OS info", JOptionPane.INFORMATION_MESSAGE);
	}
}
