package fileminer.model.test;


import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTree;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.Node;
import fileminer.model.archiver.Archiver;
import fileminer.model.archiver.ArchiverZIP;
import net.lingala.zip4j.exception.ZipException;


/**
 * @author Daniele
 * Little class to test some features
 */
public class Test {

    private static final String NAME_DIR = "FileMinerDIR";
    private static final String NAME_FILE = "FileMinerFILE.txt";
    private static final String NAME_ARCH = "FileMinerArch";


    
    @org.junit.Test
    public void modelTest() {
        FileMinerLogger logger = FileMinerLogger.getInstance();
        FileSystemTreeImpl fst = new FileSystemTreeImpl();
        FileOperationsImpl fo = new FileOperationsImpl(fst);
        Archiver archivezip = new ArchiverZIP();

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

        // Create archive of dir
        /*String arcPath = System.getProperty("user.home") 
                + System.getProperty("file.separator") + NAME_DIR
                + System.getProperty("file.separator") + NAME_ARCH + ".zip";
        
        try {
            if (!FileUtils.getFile(arcPath).exists()) {
                archivezip.compress(list, NAME_ARCH, tpDir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ZipException e) {
            e.printStackTrace();
        }
        
        assertTrue(FileUtils.getFile(arcPath).exists());*/
        
        // Remove all
        try {
            fo.remove(list, tpHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(FileUtils.getFile(dirPath).exists());
        assertFalse(FileUtils.getFile(filePath).exists());


    }


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
