package fileminer.model;

import java.io.File;
import java.nio.file.Path;

// Interfaccia per le varie operazioni sui files
/**
 * @author Daniele
 *
 */
public interface FileOperations {

	/**
	 * @param src Percorso file sorgente
	 */
	void copy(File src);
	

	/**
	 * @param path Percorso dove incollare
	 */
	void pasteTo(String path);
	
	/**
	 * 
	 */
	void cut();
	
	/**
	 * @param path Percorso dove spostare
	 */
	void moveTo(String path);
	
	/**
	 * 
	 */
	void open();
	
	/**
	 * 
	 */
	void remove();
	
	/**
	 * 
	 */
	void print();
	
	/**
	 * @return file
	 */
	File getFileSrc();
	
	
	
	
}
