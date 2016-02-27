package fileminer.model.archiver;

import java.io.FileNotFoundException;
import java.util.List;

import net.lingala.zip4j.exception.ZipException;

/**
 * @author Daniele
 *
 */
public interface Archiver {


	
	/**
	 * @param paths
	 * @param name
	 * @param dest
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	void compress( List<String> paths,  String name,  String dest) throws FileNotFoundException, ZipException;
	
	
	/**
	 * @param archive
	 */
	void decompress(List<String> archives, String dest) throws ZipException;
	
}
