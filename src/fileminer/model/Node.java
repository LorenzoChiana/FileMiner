package fileminer.model;

import java.io.File;

/**
 * @author Daniele
 *	Nodo dell'albero del file system.
 */
public class Node {
	private File file;
	private String fileName;
	private String filePath;
	private boolean writable;
	private boolean readable;
	private boolean executable;
	private long size;
	
	
	
	/**
	 * Inizializzazione nodo.
	 * @param file
	 */
	public Node(final File file) {
		this.file = file;
		this.fileName = file.getName();
		this.filePath = file.getAbsolutePath();
		this.writable = file.canWrite();
		this.readable = file.canRead();
		this.executable = file.canExecute();
		this.size = file.getTotalSpace();
		
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

	/**
	 * @return writable
	 */
	public boolean isWritable() {
		return writable;
	}

	/**
	 * @param writable
	 */
	public void setWritable(final boolean writable) {
		this.writable = file.canWrite();
	}

	/**
	 * @return readable
	 */
	public boolean isReadable() {
		return readable;
	}

	/**
	 * @param readable
	 */
	public void setReadable(final boolean readable) {
		this.readable = file.canRead();
	}

	/**
	 * @return executable
	 */
	public boolean isExecutable() {
		return executable;
	}

	/**
	 * @param executable
	 */
	public void setExecutable(final boolean executable) {
		this.executable = file.canExecute();
	}

	/**
	 * @return size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(final long size) {
		this.size = file.getTotalSpace();
	}

	/**
	 * @return file
	 */
	public File getFile() {
		return file;
	}
	
	@Override
    public String toString() {
        String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;
        }
    }
	
}
