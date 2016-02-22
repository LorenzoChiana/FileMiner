package fileminer.controller;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {
    /**
     * 
     * @return the file system tree
     */
    DefaultMutableTreeNode getFileSystemTree();
    
    /**
     * 
     * @param command
     *          String id of command to invoke
     */
    void invokesCommand(String command);
}
