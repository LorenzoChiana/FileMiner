package fileminer.view;

import java.util.List;

public interface DefaultGUI {

	/**
	 * Return a list of string corresponding to the selected nodes paths.
	 * @return List of String
	 */
	List<String> getSelectedItems();

	/**
	 * Return the path of the current directory.
	 * @return String
	 */
	String getCurrentDir();
}
