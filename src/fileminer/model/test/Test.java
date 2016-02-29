package fileminer.model.test;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;


public class Test {

	private static final String NAME = "FileMinerApp";

	@org.junit.Test
	public void test() {
		FileSystemTreeImpl fsti = new FileSystemTreeImpl();
		DefaultTreeModel model = fsti.getTree();
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode(model); 
		FileOperationsImpl foi = new FileOperationsImpl(fsti);
		final Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"));
		tree.add(new DefaultMutableTreeNode(new File(path.toUri())));
		TreePath tp = new TreePath(tree.getLastLeaf().getUserObjectPath());
		


	}
	//Crea cartella desktop
	//Crea file
	//Copia cartella desktop
	//incolla nel desktop
	//Comprimi
	//Decomprimi
	//Rimuovi tutto

}
