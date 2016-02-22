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
}
