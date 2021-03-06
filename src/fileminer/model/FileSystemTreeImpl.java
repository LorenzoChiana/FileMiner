
package fileminer.model;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * @author Daniele Gambaletta
 * Class to manage tree.
 */
public class FileSystemTreeImpl implements FileSystemTree {

	private DefaultTreeModel treeModel;
	private boolean treeReady;

	/**
	 * Initialization.
	 */
	public FileSystemTreeImpl() {
		this.treeReady = false;
	}

	@Override
	public DefaultTreeModel getTree() {

		if (treeReady) {
			return treeModel;
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

			// Aggiungo le root
			for (final Node fsRoot : roots) {
				final DefaultMutableTreeNode node = new DefaultMutableTreeNode(fsRoot);
				rootNode.add(node);
			}

			// Aggiungo figli
			try {
                addChildren(rootNode);
                addGrandChildren(rootNode);
            } catch (NullPointerException e) {
            }

			this.treeModel = new DefaultTreeModel(rootNode); 
			treeReady = true;

			return treeModel;
		}
	}

    @Override
    public void reloadTreeByNode(final DefaultMutableTreeNode rootNode) {
        treeModel.reload(rootNode);
    }

    @Override
    public void addNodesToTree(final DefaultMutableTreeNode rootNode, final List<File> files) {
        for (final File file : files) {
            final DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new Node(file));
            rootNode.add(childNode);
        }
        sortNodes(rootNode);
    }

    @Override
    public void moveNodes(final DefaultMutableTreeNode rootNode, final List<DefaultMutableTreeNode> oldNodes) {
        for (final DefaultMutableTreeNode oldNode : oldNodes) {
            rootNode.add(oldNode);
        }
        sortNodes(rootNode);
    }

    /**
     * Remove nodes.
     * @param rootNode
     * @param nodes
     */
    public void removeNodes(final DefaultMutableTreeNode rootNode, final List<DefaultMutableTreeNode> nodes) {
        for (final DefaultMutableTreeNode node : nodes) {
            rootNode.remove(node);
        }
    }

    /**
     * Sort nodes by ascending order.
     * @param rootNode
     */
	private void sortNodes(final DefaultMutableTreeNode rootNode) {
	    @SuppressWarnings("unchecked")
        final ArrayList<DefaultMutableTreeNode> children = Collections.list(rootNode.children());
        final ArrayList<String> orgCnames = new ArrayList<String>();
        final ArrayList<String> cNames = new ArrayList<String>();
        final DefaultMutableTreeNode temParent = new DefaultMutableTreeNode();

        for (final DefaultMutableTreeNode child : children) {
            temParent.insert(child, 0);
            cNames.add(child.toString().toUpperCase());
            orgCnames.add(child.toString().toUpperCase());
        }

        Collections.sort(cNames);
        for (final String name : cNames) {
            final int indx = orgCnames.indexOf(name);
            rootNode.insert(children.get(indx), rootNode.getChildCount());
        }
    }

    @Override
	public void addGrandChildren(final DefaultMutableTreeNode root) {
        @SuppressWarnings("unchecked")
        final Enumeration<DefaultMutableTreeNode> enumeration = root.children();
        while (enumeration.hasMoreElements()) {
            final DefaultMutableTreeNode node = enumeration.nextElement();
            addChildren(node);
        }
    }

	@Override
	public void addChildren(final DefaultMutableTreeNode rootNode) {		
		@SuppressWarnings("unchecked")
        final Enumeration<DefaultMutableTreeNode> enumeration = rootNode.children();
		while (enumeration.hasMoreElements()) {
			final DefaultMutableTreeNode node = enumeration.nextElement();
			final Node n = (Node) node.getUserObject();
			final File file = n.getFile();
			   
			try {
				for (final File child : file.listFiles()) {
					node.add(new DefaultMutableTreeNode(new Node(child)));
				}
			} catch (NullPointerException e) {
			}
		}
	}

	/**
	 * Print tree for tests.
	 */
	public static void printTree(final DefaultMutableTreeNode root) {
		@SuppressWarnings("unchecked")
		final Enumeration<DefaultMutableTreeNode> en = root.preorderEnumeration();
		while (en.hasMoreElements()) {
			final DefaultMutableTreeNode node = en.nextElement();
			final TreeNode[] path = node.getPath();
			System.out.println((node.isLeaf() ? "  - " : "+ ") + path[path.length - 1]);
		}
	}

    /**
     * Reload node from path.
     * @param currentDir
     */
    public void refreshFromPath(final TreePath currentDir) {
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) currentDir.getLastPathComponent();
        treeNode.removeAllChildren();

        final Node node = (Node) treeNode.getUserObject();
        final File file = node.getFile();

        try {
            for (final File child : file.listFiles()) {
                treeNode.add(new DefaultMutableTreeNode(new Node(child)));
            }
        } catch (NullPointerException e) {
        }

        addGrandChildren(treeNode);
        reloadTreeByNode(treeNode);
    }
}

