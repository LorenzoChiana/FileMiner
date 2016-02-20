package fileminer.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe per la creazione dell'albero del FileSystem.
 * @author Daniele
 *
 */
public interface FileBrowser {


	/**
	 * Metodo per inserire file e cartelle del FileSystem nell'albero.
	 * @return DefaultMutableTreeNode
	 */
	DefaultMutableTreeNode getTree();


	/**
	 * Aggiunge files alle sottocartelle all'albero.
	 * @param rootNode root dell'albero
	 */
	void addGrandChilds(DefaultMutableTreeNode rootNode);

	/**
	 * Aggiunge i file della root all'albero.
	 * @param rootNode root dell'albero
	 */
	void addChilds(DefaultMutableTreeNode rootNode);


}
