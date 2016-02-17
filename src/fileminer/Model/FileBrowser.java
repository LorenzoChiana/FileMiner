package fileminer.Model;

import javax.swing.tree.DefaultMutableTreeNode;

// Classe per la creazione del Tree del FileSystem
/**
 * 
 * @author Daniele
 *
 */
public interface FileBrowser {
    
    
    /**
     * @return null
     */
    DefaultMutableTreeNode setTree();
    
   
    /**
     * @param rootNode nodo root
     */
    void addGrandChilds(DefaultMutableTreeNode rootNode);
    
    /**
     * @param rootNode nodo root
     */
    void addChilds(DefaultMutableTreeNode rootNode);


}
