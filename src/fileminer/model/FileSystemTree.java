package fileminer.model;

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
