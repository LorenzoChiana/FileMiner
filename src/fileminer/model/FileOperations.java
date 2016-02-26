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
	 * @param srcPath
	 */
	void copy(List<String> srcPath);



	/**
	 * Paste the file in the directory path. 
	 * @param destPath Directory destination path
	 * @throws IOException
	 */
	void pasteTo(String destPath) throws IOException;



	/**
	 * Move a file in the directory path.
	 * @param srcPath File or directory source path
	 * @param destPath Directory destination path
	 * @throws IOException
	 */
	void moveTo(String srcPath, String destPath) throws IOException;


	/**
	 * Open a file.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void open(String srcPath) throws IOException;;


	/**
	 * Remove a file or an entirely directory.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void remove(String srcPath) throws IOException;


	/**
	 * Print a file if printable.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void print(String srcPath) throws IOException;



}
