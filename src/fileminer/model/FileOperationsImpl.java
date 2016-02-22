package fileminer.model;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

import sun.text.resources.FormatData;

/**
 * Classe per le varie operazioni sui files.
 */
public class FileOperationsImpl implements FileOperations {

	String srcPath;
	
	public String getSrc(){
		return srcPath;
	}
	
	@Override
	public void copy(final String srcPath) {
		this.srcPath = srcPath;
	}

	@Override
	public void pasteTo(final String destPath) throws IOException {
		File src = new File(this.srcPath); // File o Directory
		File dest = new File(destPath); // Directory
		
		
		if (src.isDirectory()) {
			FileUtils.copyDirectory(src, dest);
		} else {
			FileUtils.copyFile(src, dest);			
		}
		
	}

	@Override
	public void moveTo(final String srcPath, final String destPath) throws IOException {
		File src = new File(srcPath); // File o Directory
		File dest = new File(destPath); // Directory
		
		if (src.isDirectory()) {
			FileUtils.moveDirectory(src, dest);
		} else {
			FileUtils.moveFile(src, dest);
		}
	}

	@Override
	public void open(String srcPath) throws IOException{
	}

	@Override
	public void remove(final String srcPath) throws IOException {
		File src = new File(srcPath); // File o Directory
	
		if (src.isDirectory()) {
			FileUtils.deleteDirectory(src);
		} else {
			FileUtils.forceDelete(src);
		}
	}

	@Override
	public void print(String srcPath) throws IOException{
	}
		
}
