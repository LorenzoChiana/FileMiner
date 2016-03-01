package fileminer.controller;

import fileminer.model.Bookmark;
import fileminer.model.FileSystemTreeImpl;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {
    
    /**
     * 
     * @param command
     *          id of command to invoke
     */
    void invokesCommand(Commands command);

    /**
     * 
     * @return the file system object
     */
    FileSystemTreeImpl getFileSystem();

    /**
     * @return chronology
     */
    Chronology getChronology();

    /**
     * 
     * @return bookmarks
     */
    Bookmark getBookmarks();

    /**
     * Print OS information.
     * 
     * @return OS information.
     */
    String getOSInfo();

    /**
     * Exit from application.
     */
    void quit();
}
