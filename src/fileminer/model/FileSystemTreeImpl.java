
package fileminer.model;

import java.io.File;
import java.util.Enumeration;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;


/**
 * Classe per la creazione del Tree del FileSystem.
 */
public class FileSystemTreeImpl implements FileSystemTree {

	private FileSystemView fsv;
	private DefaultMutableTreeNode tree;

	/**
	 * Costruttore di FileBrowserImpl inizializza la FileSystemView.
	 */
	public FileSystemTreeImpl() {
		this.fsv = FileSystemView.getFileSystemView();
	}


	@Override
	public DefaultMutableTreeNode getTree() {

		// Creo il nodo root
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();

		// Ottengo le roots
		File[] roots = fsv.getRoots();

		for (File fsRoot : roots) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(fsRoot);
			rootNode.add(node);
			File[] files = fsv.getFiles(fsRoot, true);
			for (File file : files) {
				if (file.isDirectory()) {
					node.add(new DefaultMutableTreeNode(file));
				}
			}
		}

		try {
			addChildren(rootNode);
			addGrandChildren(rootNode);
		} catch (NullPointerException e) {
		}

		//Test di funzionamento
		printTree(rootNode);
		
		this.tree = new DefaultMutableTreeNode(rootNode); 

		return tree;
	}


	@Override
	public void addGrandChildren(final DefaultMutableTreeNode rootNode) {
		Enumeration<?> enumeration = rootNode.children();

		// Aggiungo figli dei figli ai vari nodi se presenti
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
			addChildren(node);
		}
	}        


	@Override
	public void addChildren(final DefaultMutableTreeNode rootNode) {		
		Enumeration<?> enumeration = rootNode.children();
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = 
					(DefaultMutableTreeNode) enumeration.nextElement();
			File file = (File) node.getUserObject();
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					node.add(new DefaultMutableTreeNode(
							child));
				}
			}
		}
	}

	/**
	 * @return FileSystemView
	 */
	public FileSystemView getView() {
		return fsv;
	}
	
	
	/**
	 * @return DefaultMutableTreeNode
	 */
	public DefaultMutableTreeNode getTreeNode() {
		return this.tree;
	}


	/**
	 * Stampa l'albero del FileSystem per i test.
	 */
	private static void printTree(final DefaultMutableTreeNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> en = root.preorderEnumeration();
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode node = en.nextElement();
			TreeNode[] path = node.getPath();
			System.out.println((node.isLeaf() ? "  - " : "+ ") + path[path.length - 1]);
		}
	}

}

