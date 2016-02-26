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
	/*
	private boolean writable;
	private boolean readable;
	private boolean executable;
	private long size;
	*/
	
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
		/*
		this.writable = file.canWrite();
		this.readable = file.canRead();
		this.executable = file.canExecute();
		this.size = file.getTotalSpace();
		*/
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

//	/**
//	 * @return writable
//	 */
//	public boolean isWritable() {
//		return writable;
//	}
//
//	/**
//	 * @param writable
//	 */
//	public void setWritable(final boolean writable) {
//		this.writable = file.canWrite();
//	}
//
//	/**
//	 * @return readable
//	 */
//	public boolean isReadable() {
//		return readable;
//	}
//
//	/**
//	 * @param readable
//	 */
//	public void setReadable(final boolean readable) {
//		this.readable = file.canRead();
//	}
//
//	/**
//	 * @return executable
//	 */
//	public boolean isExecutable() {
//		return executable;
//	}
//
//	/**
//	 * @param executable
//	 */
//	public void setExecutable(final boolean executable) {
//		this.executable = file.canExecute();
//	}
//
//	/**
//	 * @return size
//	 */
//	public long getSize() {
//		return size;
//	}
//
//	/**
//	 * @param size
//	 */
//	public void setSize(final long size) {
//		this.size = file.getTotalSpace();
//	}
//
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
