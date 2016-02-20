package fileminer.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Classe per la creazione del Tree del FileSystem
 * @author Daniele
 *
 */
public interface FileBrowser {


	/**
	 * Metodo per inserire il FileSystem nell'albero.
	 * @return DefaultMutableTreeNode
	 */
	DefaultMutableTreeNode getTree();


	/**
	 * Aggiunge file alle sottocartelle all'albero.
	 * @param rootNode root dell'albero
	 */
	void addGrandChilds(DefaultMutableTreeNode rootNode);

	/**
	 * Aggiunge i file della root all'albero.
	 * @param rootNode root dell'albero
	 */
	void addChilds(DefaultMutableTreeNode rootNode);


}
