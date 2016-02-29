package fileminer.model;

import java.io.File;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Classe per la creazione dell'albero del FileSystem.
 * @author Daniele
 *
 */
public interface FileSystemTree {


	/**
	 * Metodo per inserire file e cartelle del FileSystem nell'albero.
	 * @return DefaultMutableTreeNode
	 */
	DefaultTreeModel getTree();

	/**
     * Aggiunge i file dal nodo passatogli per aggiornare l'alberto dopo un operazione.
     * @param rootNode 
     * @param files 
     */
    void addNodesToTree(DefaultMutableTreeNode rootNode, List<File> files);

    /**
     * Sposta i vecchi nodi 
     * @param rootNode
     * @param oldNodes
     */
    void moveNodes(DefaultMutableTreeNode rootNode, List<DefaultMutableTreeNode> oldNodes);

	/**
	 * @param rootNode
	 */
	void reloadTreeByNode(DefaultMutableTreeNode rootNode);

	/**
	 * Aggiunge files alle sottocartelle all'albero.
	 * @param rootNode root dell'albero
	 */
	void addGrandChildren(DefaultMutableTreeNode rootNode);

	/**
	 * Aggiunge i file della root all'albero.
	 * @param rootNode root dell'albero
	 */
	void addChildren(DefaultMutableTreeNode rootNode);
}
