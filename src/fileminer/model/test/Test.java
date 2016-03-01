package fileminer.model.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;

import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;


/**
 * @author Daniele
 * Little class to test some features.
 */
public class Test {

    private static final String NAME_DIR = "FileMinerDIR";
    private static final String NAME_FILE = "FileMinerFILE.txt";
    
    /**
     * Little test method.
     */
    @org.junit.Test
    public void modelTest() {
        FileSystemTreeImpl fst = new FileSystemTreeImpl();
        FileOperationsImpl fo = new FileOperationsImpl(fst);

        DefaultTreeModel model = fst.getTree();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();



        // Create dir
        TreePath tpHome = find(root, FileUtils.getFile(System.getProperty("user.home")));
        String dirPath = System.getProperty("user.home") 
                + System.getProperty("file.separator") + NAME_DIR;
        try {
            if (!FileUtils.getFile(dirPath).exists()) {
                fo.mkDir(tpHome, NAME_DIR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(FileUtils.getFile(dirPath).exists());

        
        // Create file inside dir
        
        TreePath tpDir = find(root, FileUtils.getFile(System.getProperty("user.home") 
                + System.getProperty("file.separator") + NAME_DIR));
        String filePath = System.getProperty("user.home") 
                + System.getProperty("file.separator") + NAME_DIR
                + System.getProperty("file.separator") + NAME_FILE;
        
        try {
            if (!FileUtils.getFile(filePath).exists()) {
                fo.mkFile(tpDir, NAME_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assertTrue(FileUtils.getFile(filePath).exists());
        
        // Create list for archive
        List<TreePath> list = new ArrayList<>();
        list.add(tpDir);

         
        // Remove all
        try {
            fo.remove(list, tpHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(FileUtils.getFile(dirPath).exists());
        assertFalse(FileUtils.getFile(filePath).exists());


    }


    
    /**
     * Return the TreePath of file in the tree
     * @param root
     * @param f
     * @return tree path 
     */
    private TreePath find(final DefaultMutableTreeNode root, final File f) {
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            Node n = (Node) node.getUserObject();
            File file = n.getFile();
            if (file.equals(f)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }
}
