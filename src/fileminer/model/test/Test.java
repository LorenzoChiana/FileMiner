package fileminer.model.test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;


public class Test {
	
	private static final String NAME = "FileMinerApp";
	
	@org.junit.Test
	public void testOK() {
		FileSystemTreeImpl fsti = new FileSystemTreeImpl();
		DefaultTreeModel model = fsti.getTree();
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode(model); 
		FileOperationsImpl foi = new FileOperationsImpl(fsti);
		String dir = System.getProperty("user.home")+ System.getProperty("file.separator") + NAME;
		
		
       
		
		
		
	}
	//Crea cartella desktop
	//Crea file
	//Copia cartella desktop
	//incolla nel desktop
	//Comprimi
	//Decomprimi
	//Rimuovi tutto

}
