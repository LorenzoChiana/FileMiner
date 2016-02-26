package fileminer.view.components;

import javax.swing.JOptionPane;

import fileminer.controller.Controller;

public class OSInfoDialog {

	final String text;

	public OSInfoDialog(final Controller ctrl) {
		text = ctrl.getOSInfo();
	}

	public void openDialog() {
		JOptionPane.showMessageDialog(null, text, "OS info", JOptionPane.INFORMATION_MESSAGE);
	}
}
