
package fileminer.model;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.apache.commons.io.filefilter.DirectoryFileFilter;


/**
 * Classe per la creazione del Tree del FileSystem.
 */
public class FileSystemTreeImpl implements FileSystemTree {

	private final FileSystemView fsv;
	private DefaultTreeModel tree;
	private boolean treeReady;

	/**
	 * Costruttore di FileBrowserImpl inizializza la FileSystemView.
	 */
	public FileSystemTreeImpl() {
		this.fsv = FileSystemView.getFileSystemView();
		this.treeReady = false;
	}


	@Override
	public DefaultTreeModel getTree() {

		if (treeReady) {
			return tree;
		} else {
			// Creo il nodo root
			final DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
			List<File> roots = new ArrayList<>();


			// HOME
			Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "");	
			roots.add(new File(path.toUri()));
			
			//Desktop
			path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Desktop");	
			roots.add(new File(path.toUri()));
			
			//Desktop
			path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Documents");	
			roots.add(new File(path.toUri()));
			
			
			// Partizioni
			for(File file : File.listRoots()){
				roots.add(file);
			}

			
			for (final File fsRoot : roots) {
				final DefaultMutableTreeNode node = new DefaultMutableTreeNode(fsRoot);
				rootNode.add(node);
			}


			addChildren(rootNode);
	        

			//Test di funzionamento
			printTree(rootNode);

			this.tree = new DefaultTreeModel(rootNode); 
			treeReady = true;

			return tree;
		}
	}


	@Override
	public void addGrandChildren(final DefaultMutableTreeNode rootNode) {
		final Enumeration<?> enumeration = rootNode.children();

		// Aggiungo figli dei figli ai vari nodi se presenti
		while (enumeration.hasMoreElements()) {
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
			addChildren(node);
		}
	}        


	@Override
	public void addChildren(final DefaultMutableTreeNode rootNode) {		
		final Enumeration<?> enumeration = rootNode.children();
		while (enumeration.hasMoreElements()) {
			final DefaultMutableTreeNode node = 
					(DefaultMutableTreeNode) enumeration.nextElement();
			final File file = (File) node.getUserObject();
			if (file.isDirectory()) {
				for (final File child : file.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY)) {
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
	public DefaultTreeModel getTreeNode() {
		return this.tree;
	}


	/**
	 * Stampa l'albero del FileSystem per i test.
	 */
	private static void printTree(final DefaultMutableTreeNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> en = root.preorderEnumeration();
		while (en.hasMoreElements()) {
			final DefaultMutableTreeNode node = en.nextElement();
			final TreeNode[] path = node.getPath();
			System.out.println((node.isLeaf() ? "  - " : "+ ") + path[path.length - 1]);
		}
	}

}

