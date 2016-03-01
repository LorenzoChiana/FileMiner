package fileminer.model.archiver;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import javax.swing.tree.TreePath;

import net.lingala.zip4j.exception.ZipException;

/**
 * @author Daniele Gambaletta
 * Archiver interface for multi-type compression
 */
public interface Archiver {


    /**
     * Compression method.
     * @param paths files to compress
     * @param name name of archive
     * @param dest destination of archive
     * @throws FileNotFoundException file not found
     * @throws ZipException zip exception
     * @throws FileAlreadyExistsException 
     */
    void compress(List<TreePath> paths,  String name,  TreePath dest) throws FileNotFoundException, ZipException;


    
    /**
     * Decompression method.
     * @param archives archives to decompress.
     * @param dest destination of decompressed files
     * @throws FileNotFoundException file not found
     * @throws ZipException zip exception
     * @throws FileAlreadyExistsException 
     */
    void decompress(List<TreePath> archives, TreePath dest) throws FileNotFoundException, ZipException;

}
