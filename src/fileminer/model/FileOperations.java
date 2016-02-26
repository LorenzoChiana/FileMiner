package fileminer.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

// Interfaccia per le varie operazioni sui files
/**
 *
 */
public interface FileOperations {


	/**
	 * Copy the source path to the local Clipboard.
	 * @param isCopy
	 * @param srcPath
	 */
	void copy(List<String> srcPath, final boolean isCopy);


	/**
	 * Paste the file in the directory path. 
	 * @param destPath
	 * @throws IOException
	 */
	void pasteTo(final String destPath) throws IOException;



	/**
	 * Open a file.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void open(String srcPath) throws IOException;;


	/**
	 * Remove a file or an entirely directory.
	 * @param clipboard Files or directories source path
	 * @throws IOException
	 */
	void remove(final List<String> clipboard) throws IOException;


	/**
	 * Print a file if printable.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void print(String srcPath) throws IOException;



}
