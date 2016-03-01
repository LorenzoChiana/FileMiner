package fileminer.model;

import java.io.IOException;
import java.util.List;

import javax.swing.tree.TreePath;

// Interfaccia per le varie operazioni sui files
/**
 *
 */
public interface FileOperations {

	/**
	 * Paste the file in the directory path. 
	 * @param clipboard 
	 * @param destPath 
	 * @param isCopy 
	 * @throws IOException 
	 */
	void pasteTo(List<TreePath> clipboard, TreePath destPath, boolean isCopy) throws IOException;



	/**
	 * Open a file.
	 * @param srcPath File or directory source path
	 * @throws IOException 
	 */
	void open(List<TreePath> srcPath) throws IOException;


	/**
	 * Remove a file or an entirely directory.
	 * @param clipboard Files or directories source path
	 * @param srcPath 
	 * @throws IOException 
	 */
	void remove(List<TreePath> clipboard, TreePath srcPath) throws IOException;

	
	/**
	 * @param srcPath 
	 * @param name 
	 * @throws IOException 
	 */
	void mkDir(TreePath srcPath, String name) throws IOException;

	
	/**
	 * @param srcPath 
	 * @param name 
	 * @throws IOException 
	 */
	void mkFile(TreePath srcPath, String name) throws IOException;

	
	/**
	 * @param srcTarget 
	 * @param srcLink 
	 * @param name 
	 * @throws IOException 
	 */
	void mkLink(TreePath srcTarget, TreePath srcLink, String name) throws IOException;

//    void update(TreePath dirPath, TreePath newFilePath, TreePath oldFilePath);

}
