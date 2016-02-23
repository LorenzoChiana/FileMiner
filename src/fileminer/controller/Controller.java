package fileminer.controller;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {
    /**
     * 
     * @return the file system tree
     */
    DefaultTreeModel getFileSystemTree();
    
    /**
     * 
     * @param command
     *          id of command to invoke
     * @param srcPath
     *          File source path 
     * @param destPath
     *          Destination path (only for operations like copy, cut, moveto...)
     */
    void invokesCommand(Commands command, String srcPath, String destPath);

}
