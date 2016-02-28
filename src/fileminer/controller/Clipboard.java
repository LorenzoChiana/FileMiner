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
    
    /**
     * 
     * @return true if clipboard is empty.
     */
    boolean isEmpty();
    /**
     * 
     * @param isCopy
     *          true if is copy
     *          false if is cut
     */
    void setParameter(boolean isCopy);
    /**
     * 
     * @return a boolean that represents if it is copy or cut operation.
     */
    boolean getParameter();
}
