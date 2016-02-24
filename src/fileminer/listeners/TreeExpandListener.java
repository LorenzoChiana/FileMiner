package fileminer.listeners;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import fileminer.model.FileSystemTree;
import fileminer.model.runnable.AddFileNodes;

public class TreeExpandListener implements TreeWillExpandListener {

    //private final FileSystemTree modelTree;

    /*
    public TreeExpandListener(final DefaultMutable ) {
        modelTree = fst;
    }
    */

    @Override
    public void treeWillCollapse(final TreeExpansionEvent event) throws ExpandVetoException {
        
    }

    @Override
    public void treeWillExpand(final TreeExpansionEvent event) throws ExpandVetoException {
        
    }

}
