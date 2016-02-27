package fileminer.model.archiver;

import java.io.File;
import java.util.List;

/**
 * @author Daniele
 *
 */
public interface Archiver {


	/**
	 * @param files
	 * @param name
	 * @param dest
	 */
	void compress(List<String> files, String name, String dest);
	
	
	/**
	 * @param archive
	 */
	void decompress(String archive, String dest);
	
}
