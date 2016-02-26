package fileminer.listeners;

import java.io.File;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;
import fileminer.model.runnable.AddFileNodes;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class TreeNodeSelectionListener implements TreeSelectionListener {
    private final FileSystemTreeImpl model;
    private final FileMinerGUI view;
    
    /**
     * 
     * @param model
     *          file system tree.
     * @param view
     *          GUI.
     */
    public TreeNodeSelectionListener(final FileSystemTreeImpl model, final FileMinerGUI view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        final Node fileNode = (Node) treeNode.getUserObject();
        
        final AddFileNodes addFileNodes = new AddFileNodes(this.model, treeNode);
        new Thread(addFileNodes).start();
        
        final File file = fileNode.getFile();
        
        //view.metodo che mi dà i dettagli del file selezionato.
        if (file.isDirectory()) {
            //view.metodo fa visualizzare il suo contenuto
        }
    }

}
