package fileminer.controller;

import java.util.List;
/**
 * 
 * @author Lorenzo Chiana
 *
 */
public interface Clipboard {
    /**
     * 
     * @return the list of path's files to copy
     */
    List<String> getPathFiles();
    /**
     * 
     * @param path
     *          method for adding file into the clipboard.
     */
    void addPathFiles(List<String> path);
    /**
     * method for erase files on clipboard.
     */
    void clean();
}
