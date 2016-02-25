package fileminer.model;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Classe per le varie operazioni sui files.
 */
public class FileOperationsImpl implements FileOperations {

	private String srcPath;

	
	/**
	 * @return path of source file
	 */
	public String getSrc() {
		return srcPath;
	}

	@Override
	public void copy(final String srcPath) {
		this.srcPath = srcPath;
	}

	@Override
	public void pasteTo(final String destPath) throws IOException {
		File src = FileUtils.getFile(this.srcPath); // File o Directory
		File dest = FileUtils.getFile(destPath); // Directory

		if (src.isDirectory()) {
			FileUtils.copyDirectory(src, dest);
		} else {
			FileUtils.copyFileToDirectory(src, dest);			
		}

	}

	@Override
	public void moveTo(final String srcPath, final String destPath, final boolean createDestDir) throws IOException {
		File src = FileUtils.getFile(srcPath); // File o Directory
		File dest = FileUtils.getFile(destPath); // Directory

		if (src.isDirectory()) {
			FileUtils.moveDirectory(src, dest);
		} else {
			FileUtils.moveFileToDirectory(src, dest, createDestDir);
		}
	}

	@Override
	public void open(final String srcPath) throws IOException{
	}

	@Override
	public void remove(final String srcPath) throws IOException {
		File src = FileUtils.getFile(srcPath); // File o Directory

		if (src.isDirectory()) {
			FileUtils.deleteDirectory(src);
		} else {
			FileUtils.forceDelete(src);
		}
	}

	@Override
	public void print(final String srcPath) throws IOException{
	}

}
