package fileminer.model;

import java.io.File;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

/**
 * @author Daniele Gambaletta
 * Node of the tree.
 */
public class Node {
	private final File file;
	private boolean hasGenerated;
	private Icon fileIcon;
	private String fileName;
	private String filePath;
	
	
	/**
	 * Initializations.
	 * @param file file to add in the node
	 */
	public Node(final File file) {
		this.file = file;
		this.hasGenerated = false;
		this.filePath = file.getAbsolutePath();
		if (FileSystemView.getFileSystemView().isFileSystemRoot(file)
		    || filePath.equals(System.getProperty("user.home"))
		    || file.isFile()) {
		    this.fileName = FileSystemView.getFileSystemView().getSystemDisplayName(file);
		    this.fileIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
		} else {
		    this.fileName = file.getName();
		    this.fileIcon = UIManager.getIcon("FileView.directoryIcon");
		}
		
	}

	/**
	 * Check if node has already generated nodes.
	 * @return boolean
	 */
	public boolean hasNodeGenerated() {
	    return this.hasGenerated;
	}

	/**
	 * Set that node has generated nodes.
	 * @param f flag
	 */
	public void setNodeHasGenerated(final boolean f) {
	    this.hasGenerated = true;
	}
	
	/**
	 * Get file name.
	 * @return file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set file name.
	 */
	public void setFileName() {
		this.fileName = file.getName();
	}

	/**
	 * Get file path.
	 * @return file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set file path.
	 */
	public void setFilePath() {
		this.filePath = file.getAbsolutePath();
	}

	/**
	 * Get file icon.
	 * @return file icon
	 */
	public Icon getFileIcon() {
	    return this.fileIcon;
	}

	/**
	 * Get file.
	 * @return file
	 */
	public File getFile() {
		return file;
	}
	
	@Override
    public String toString() {
        final String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
	
}
