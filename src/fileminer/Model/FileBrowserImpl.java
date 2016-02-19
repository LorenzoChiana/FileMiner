
package fileminer.Model;

import java.io.File;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;


// Classe per la creazione del Tree del FileSystem
/**
 * @author Daniele
 *
 */
public class FileBrowserImpl implements FileBrowser {

    private FileSystemView fsv;

    /**
     * 
     */
    public FileBrowserImpl() {
        this.fsv = FileSystemView.getFileSystemView();
    }


    /**
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode setTree() {

        // Creo il nodo root
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

        // Assegno la root
        for (File file : fsv.getRoots()) {
            rootNode.add(new DefaultMutableTreeNode(new NodeImpl(file)));
        }

        addChilds(rootNode);
        addGrandChilds(rootNode);

        return new DefaultMutableTreeNode(rootNode); 
    }


    @Override
    public void addGrandChilds(final DefaultMutableTreeNode rootNode) {
        Enumeration<?> enumeration = rootNode.children();
        
        // Aggiungo figli dei figli ai vari nodi se presenti
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            addChilds(node);
        }
    }        


    @Override
    public void addChilds(final DefaultMutableTreeNode rootNode) {
        Enumeration<?> enumeration = rootNode.children();
        
        // Aggiungo figli ai vari nodi
        while (enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            NodeImpl fileNode = (NodeImpl) node.getUserObject();
            File file = fileNode.getFile();
            // Controllo se è una directory
            if (file.isDirectory() && !isEmpty(file.listFiles())) {
                // Aggiungo i file nella directory se presenti
                for (File child : file.listFiles()) {
                    node.add(new DefaultMutableTreeNode(new NodeImpl(child)));
                }
            }
        }        
    }

    // Controlla se la lista files è vuota
    private static boolean isEmpty(final File[] files) {
        return files.length <= 0;
    }
    
    /**
     * @return FileSystemView
     */
    public FileSystemView getView() {
        return fsv;
    }

}

