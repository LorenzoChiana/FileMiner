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
     * @param srcPath
     *          File source path 
     * @param destPath
     *          Destination path (only for operations like copy, cut, moveto...)
     */
    void invokesCommand(String command, String srcPath, String destPath);

}
