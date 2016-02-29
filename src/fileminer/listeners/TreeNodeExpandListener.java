package fileminer.listeners;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import fileminer.model.FileSystemTreeImpl;
import fileminer.model.runnable.AddFileNodes;

/*
 * Classe per aggiornare la view quando l'albero si espande 
 * o quando si comprime un nodo.
 */
/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class TreeNodeExpandListener implements TreeWillExpandListener {

    private final FileSystemTreeImpl treeModel;
    /**
     * 
     * @param model tree of file system.
     */
    public TreeNodeExpandListener(final FileSystemTreeImpl model) {
        this.treeModel = model;
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
        final AddFileNodes addFileNodes = new AddFileNodes(treeModel, treeNode);
        new Thread(addFileNodes).start();
    }
}