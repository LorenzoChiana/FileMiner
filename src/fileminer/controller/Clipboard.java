package fileminer.controller;

import java.util.List;

import javax.swing.tree.TreePath;
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
    List<TreePath> getPathFiles();
    /**
     * 
     * @param path
     *          method for adding file into the clipboard.
     */
    void addPathFiles(List<TreePath> path);
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
     * @param paramIsCopy
     *          true if it is copy
     *          false if it is cut
     */
    void setParameter(boolean paramIsCopy);
    /**
     * 
     * @return a boolean that represents if it is copy or cut operation.
     */
    boolean getParameter();
}
