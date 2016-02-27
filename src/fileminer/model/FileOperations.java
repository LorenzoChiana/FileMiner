package fileminer.model;

import java.io.IOException;
import java.util.List;

/**
 * @author Daniele Gambaletta
 * Interface for file operations.
 */
public interface FileOperations {


	/**
	 * Copy the source path/s to the local Clipboard.
	 * @param srcPaths path/s of the various files
	 */
	void copy(List<String> srcPaths);


	/**
	 * Paste file/s in the directory path. 
	 * @param destPath directory path where paste file/s
	 * @param isCopy 
	 * 		<li>true if copy
	 * 		<li>false if cut
	 * @throws IOException input/output exceptions
	 */
	void pasteTo(String destPath, boolean isCopy) throws IOException;



	/**
	 * Open file/s using Desktop class.
	 * @param srcPaths path/s of the various files
	 * @throws IOException input/output exceptions
	 */
	void open(List<String> srcPaths) throws IOException;


	/**
	 * Remove file/s or directory/ies.
	 * @param srcPaths path/s of the various files
	 * @throws IOException input/output exceptions
	 */
	void remove(final List<String> srcPaths) throws IOException;


	/**
	 * Print a file if printable.
	 * @param srcPath path of the file
	 * @throws IOException input/output exceptions
	 */
	void print(String srcPath) throws IOException;


	/**
	 * Create a new directory.
	 * @param destPath path of the directory where put new directory.
	 * @param name name of the directory
	 * @throws IOException input/output exceptions
	 */
	void mkDir(String destPath, String name) throws IOException;


	
	/**
	 * Create a new file.
	 * @param destPath path of the directory where put new file.
	 * @param name name of the file
	 * @throws IOException input/output exceptions
	 */
	void mkFile(String destPath, String name) throws IOException;


	
	/**
	 * Create a new symbolic link.
	 * @param srcTarget path target to point with this link.
	 * @param srcLink path of this link
	 * @param name name of the link
	 * @throws IOException input/output exceptions
	 */
	void mkLink(String srcTarget, String srcLink, String name) throws IOException;

	
	
	/**
	 * Rename a file or directory.
	 * @param srcPath path of the file
	 * @param name name of new file
	 * @throws IOException input/output exceptions
	 */
	void rename(String srcPath, String name) throws IOException;

}
