package fileminer.controller;

import java.util.List;

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
    void addDirectory(String path);
    
    /**
     * 
     * @return a list of string.
     *          This list represents the directory chronology.
     */
    List<String> getChronology();
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
    String getCurrentDirectory();
}
