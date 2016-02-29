package fileminer.controller;

import java.util.List;

import javax.swing.tree.TreePath;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public interface Chronology {
    /**
     * 
     * @param path
     *          this method add the new directory into the chronology.
     */
    void addDirectory(TreePath path);
    
    /**
     * 
     * @return a list of string.
     *          This list represents the directory chronology.
     */
    List<TreePath> getChronology();
    /**
     * Go forward in the list of directory. 
     */
    void nextDir();
    /**
     * Go backward in the list of directory.
     */
    void prevDir();
    /**
     * 
     * @return the path of the current directory.
     */
    TreePath getCurrentDirectory();
}
