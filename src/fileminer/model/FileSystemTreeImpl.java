
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
			final List<Node> roots = new ArrayList<>();

			// Home
			final Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"));
			roots.add(new Node(new File(path.toUri())));
			
			// Partizioni
			for (final File file : File.listRoots()) {
				roots.add(new Node(file));
			}

			for (final Node fsRoot : roots) {
				final DefaultMutableTreeNode node = new DefaultMutableTreeNode(fsRoot);
				rootNode.add(node);
			}

			try {
                addChildren(rootNode);
                addGrandChildren(rootNode);
            } catch (NullPointerException e) {
            }

			//Test di funzionamento
			printTree(rootNode);

			this.tree = new DefaultTreeModel(rootNode); 
			treeReady = true;

			return tree;
		}
	}

	@Override
	public void addGrandChildren(final DefaultMutableTreeNode root) {
        final Enumeration<?> enumeration = root.children();
        while (enumeration.hasMoreElements()) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            addChildren(node);
        }
    }

	@Override
	public void addChildren(final DefaultMutableTreeNode rootNode) {		
		final Enumeration<?> enumeration = rootNode.children();
		while (enumeration.hasMoreElements()) {
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
			final Node n = (Node) node.getUserObject();
			final File file = n.getFile();

			final FileFilter dirFilter = new FileFilter() {
                @Override
                public boolean accept(final File pathname) {
                    return pathname.isDirectory();
                }
			};

			if (file.isDirectory()) {
			    try {
                    for (final File child : file.listFiles(dirFilter)) {
                        node.add(new DefaultMutableTreeNode(new Node(child)));
                    }
                } catch (NullPointerException e) {
                }
			}
		}
	}

	/**
	 * @return FileSystemView
	 */
	public FileSystemView getView() {
		return this.fsv;
	}

	/**
	 * Stampa l'albero del FileSystem per i test.
	 */
	public static void printTree(final DefaultMutableTreeNode root) {
		@SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> en = root.preorderEnumeration();
		while (en.hasMoreElements()) {
			final DefaultMutableTreeNode node = en.nextElement();
			final TreeNode[] path = node.getPath();
			System.out.println((node.isLeaf() ? "  - " : "+ ") + path[path.length - 1]);
		}
	}
}

