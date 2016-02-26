package fileminer.model;

import java.io.File;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

/**
 * @author Daniele
 *	Nodo dell'albero del file system.
 */
public class Node {
	private final File file;
	private boolean hasGenerated;
	private Icon fileIcon;
	private String fileName;
	private String filePath;
	
	
	/**
	 * Inizializzazione nodo.
	 * @param file
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
	 * @return file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 
	 */
	public void setFileName() {
		this.fileName = file.getName();
	}

	/**
	 * @return file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 
	 */
	public void setFilePath() {
		this.filePath = file.getAbsolutePath();
	}

	public Icon getFileIcon() {
	    return this.fileIcon;
	}

	/**
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
