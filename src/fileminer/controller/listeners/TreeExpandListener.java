package fileminer.controller.listeners;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import fileminer.model.FileSystemTree;
import fileminer.model.runnable.AddFileNodes;

/*
 * Classe per aggiornare la view quando l'albero si espande 
 * o quando si comprime un nodo.
 */
public class TreeExpandListener implements TreeWillExpandListener {

    private final FileSystemTree modelTree;

    public TreeExpandListener(final FileSystemTree modelTree) {
        this.modelTree = modelTree;
    }

    @Override
    public void treeWillCollapse(final TreeExpansionEvent event) throws ExpandVetoException {
        
    }

    @Override
    public void treeWillExpand(final TreeExpansionEvent event) throws ExpandVetoException {
        final TreePath path = event.getPath();
        // Prendo l'ultimo elemento della path.
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        // Richiamo il metodo del model per aggiungere i nodi nipoti del nodo corrente.
        final AddFileNodes addFileNodes = new AddFileNodes(this.modelTree, treeNode);
        new Thread(addFileNodes).start();
    }

}