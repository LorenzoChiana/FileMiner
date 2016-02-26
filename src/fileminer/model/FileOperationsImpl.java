package fileminer.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Classe per le varie operazioni sui files.
 */
public class FileOperationsImpl implements FileOperations {

	private List<String> clipboard;


	/**
	 * @return clipboard
	 */
	public List<String> getClipboard() {
		return clipboard;
	}


	/**
	 * @param clipboard
	 */
	public void setClipboard(final List<String> clipboard) {
		this.clipboard = clipboard;
	}

	/**
	 * Add files from path.
	 * @param files
	 */
	private List<File> addFiles(final List<File> files, List<String> clipboard) {
		for (String path : clipboard) {
			files.add(FileUtils.getFile(path)); // File o Directory
		}
		return new ArrayList<File>(files);
	}

	@Override
	public void copy(final List<String> clipboard) {
		setClipboard(clipboard);
	}

	@Override
	public void pasteTo(final String destPath, final boolean isCopy) throws IOException {

		List<File> files = new ArrayList<File>();
		File dest = FileUtils.getFile(destPath); // Directory

		files = addFiles(files, this.clipboard);

		if (isCopy) {
			for (File file : files) {
				if (file.isDirectory()) {
					FileUtils.copyDirectory(file, dest);
				} else {
					FileUtils.copyFileToDirectory(file, dest);			
				}
			}
		} else {

			for (File file : files) {
				if (file.isDirectory()) {
					FileUtils.moveDirectory(file, dest);
				} else {
					FileUtils.moveFileToDirectory(file, dest, true);
				}
			}
		}
	}


	@Override
	public void open(final String srcPath) throws IOException {
		File file = FileUtils.getFile(srcPath);
		Desktop desktop = Desktop.getDesktop();
		if (file.exists()) {
			desktop.open(file);
		}
	}

	@Override
	public void remove(final List<String> clipboard) throws IOException {
		List<File> files = new ArrayList<File>();

		files = addFiles(files, clipboard);
		
		for (File file : files) {
			if (file.isDirectory()) {
				FileUtils.deleteDirectory(file);
			} else {
				FileUtils.forceDelete(file);
			}
		}
	}

	@Override
	public void print(final String srcPath) throws IOException {
	}


}
