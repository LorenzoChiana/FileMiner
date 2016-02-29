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

	/**
	 * Inizializzazione nodo.
	 * @param f file
	 */
	public Node(final File f) {
		this.file = f;
		this.hasGenerated = false;
		if (FileSystemView.getFileSystemView().isFileSystemRoot(f)
		    || f.getAbsolutePath().equals(System.getProperty("user.home"))
		    || f.isFile()) {
		    this.fileName = FileSystemView.getFileSystemView().getSystemDisplayName(f);
		    this.fileIcon = FileSystemView.getFileSystemView().getSystemIcon(f);
		} else {
		    this.fileName = f.getName();
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
	public void setFileName(final String name) {
		this.fileName = name;
	}

	/**
	 * @return icon
	 */
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
