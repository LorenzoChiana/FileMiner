
package fileminer.Model;

import java.io.File;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;

// Classe per la creazione del Tree del FileSystem
public class FileBrowserImpl {
    private FileSystemView fsv;


    public FileBrowserImpl(){
        this.fsv = FileSystemView.getFileSystemView();
    }

    
    public DefaultMutableTreeNode setTree(){

        // Creo il nodo root
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

        // Assegno la root
        for (File file : fsv.getRoots()){ 
            rootNode.add(new DefaultMutableTreeNode(new NodeImpl(file)));
        }

        return new DefaultMutableTreeNode(rootNode); 
    }
    
    
    // Metodi per aggiungere files e directories

    
    
    
    
    
    public FileSystemView getView(){
        return fsv;
    }



}

