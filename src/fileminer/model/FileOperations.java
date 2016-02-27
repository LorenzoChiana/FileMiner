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
	 * @param destPath
	 * @param isCopy
	 * @throws IOException
	 */
	void pasteTo(final String destPath, final boolean isCopy) throws IOException;



	/**
	 * Open a file.
	 * @param srcPath File or directory source path
	 * @throws IOException
	 */
	void open(String srcPath) throws IOException;


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

	
	/**
	 * @param srcPath
	 * @param name
	 * @throws IOException
	 */
	void mkDir(String srcPath, String name) throws IOException;

	
	/**
	 * @param srcPath
	 * @param name
	 * @throws IOException
	 */
	void mkFile(String srcPath, String name) throws IOException;

	
	/**
	 * @param srcPath
	 * @param name
	 * @throws IOException
	 */
	void mkLink(String srcTarget, String srcLink, String name) throws IOException;


}
