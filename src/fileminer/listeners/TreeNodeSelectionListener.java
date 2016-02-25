package fileminer.listeners;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FileSystemTreeImpl;
import fileminer.model.runnable.AddFileNodes;
import fileminer.view.FileMinerGUI;

public class TreeNodeSelectionListener implements TreeSelectionListener {
    private final FileSystemTreeImpl model;
    private final FileMinerGUI view;
    
    public TreeNodeSelectionListener(final FileSystemTreeImpl model, final FileMinerGUI view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public void valueChanged(final TreeSelectionEvent e) {
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
        
        final AddFileNodes addFileNodes = new AddFileNodes(this.model, treeNode);
        new Thread(addFileNodes).start();
        
        //view.metodo che mi dà i dettagli del file selezionato.
    }

}
