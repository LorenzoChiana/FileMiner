package fileminer.controller;

import fileminer.model.FileSystemTreeImpl;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {
    /**
     * 
     * @return the file system object
     */
    FileSystemTreeImpl getFileSystem();
    
    /**
     * 
     * @param command
     *          id of command to invoke
     */
    void invokesCommand(Commands command);

    /**
     * Print OS information.
     */
    void printOSInfo();

    /**
     * Exit from application.
     */
    void quit();
}
