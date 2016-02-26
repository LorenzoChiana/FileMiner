package fileminer.model.archiver;

import java.io.File;

/**
 * @author Daniele
 *
 */
public interface Archiver {
	void compress(File file);
	
	void decompress(File file);
	
}
