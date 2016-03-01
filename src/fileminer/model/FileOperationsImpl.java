package fileminer.model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;

import fileminer.main.FileMinerLogger;

/**
 * Classe per le varie operazioni sui files.
 */
public class FileOperationsImpl implements FileOperations {

    private final FileSystemTreeImpl fileSystemTree;
    private final FileMinerLogger logger;

    public FileOperationsImpl(final FileSystemTreeImpl fst) {
        this.fileSystemTree = fst;
        this.logger = FileMinerLogger.getInstance();
    }

    /**
     * Add files from paths.
     * @param files
     */
    public List<File> getfileFromTP(final List<TreePath> treePaths) {
        final List<File> tmp = new ArrayList<>();
        for (final TreePath path : treePaths) {
            final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            final Node node = (Node) treeNode.getUserObject();
            tmp.add(node.getFile()); 
        }
        return tmp;
    }
    
    
    /**
     * Add file from path.
     * @param file
     */
    private File getfileFromTP(final TreePath treePath){
    	final DefaultMutableTreeNode destNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        final Node node = (Node) destNode.getUserObject();
		return node.getFile();
    }

    
    @Override
    public void pasteTo(final List<TreePath> clipboard, final TreePath destPath, final boolean isCopy) throws IOException {

        final DefaultMutableTreeNode destNode = (DefaultMutableTreeNode) destPath.getLastPathComponent();
        final Node node = (Node) destNode.getUserObject();
        final File destDir = (File) node.getFile();

        final List<File> files = this.getfileFromTP(clipboard);
        int size = files.size();

        if (isCopy) {
            int n = 1;
            for (final File file : files) {
                logger.getConsole().putStringLater("Copy of " + n + " element out of " + size + " in progress...");
                if (file.isDirectory()) {
                    FileUtils.copyDirectoryToDirectory(file, destDir);
                } else {
                    FileUtils.copyFileToDirectory(file, destDir);			
                }
                n++;
            }
            fileSystemTree.addNodesToTree(destNode, files);
        } else {

            int n = 1;
            for (final File file : files) {
                logger.getConsole().putStringLater("Move of " + n + " element out of " + size + " in progress...");
                if (file.isDirectory()) {
                    FileUtils.moveDirectoryToDirectory(file, destDir, false);
                } else {
                    FileUtils.moveFileToDirectory(file, destDir, true);
                }
                n++;
            }
            final List<DefaultMutableTreeNode> oldNodes = new ArrayList<>();
            for (final TreePath oldNode : clipboard) {
                oldNodes.add((DefaultMutableTreeNode) oldNode.getLastPathComponent());
            }
            final DefaultMutableTreeNode oldParent = (DefaultMutableTreeNode) oldNodes.get(0).getParent();
            fileSystemTree.moveNodes(destNode, oldNodes);
            fileSystemTree.reloadTreeByNode(oldParent);
        }
        fileSystemTree.reloadTreeByNode(destNode);
    }


    @Override
    public void open(final List<TreePath> srcPath) throws IOException {
        for (final TreePath filePath : srcPath) {
            final File file = this.getfileFromTP(filePath);
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()) {
            	desktop.open(file);
            }
        }
    }

    
    @Override
    public void remove(final List<TreePath> clipboard, final TreePath srcPath) throws IOException {
        final List<File> files = getfileFromTP(clipboard);

        for (final File file : files) {
            
            if (file.isDirectory()) {
                FileUtils.deleteDirectory(file);
            } else {
                FileUtils.forceDelete(file);
            }
        }

        final List<DefaultMutableTreeNode> nodeList = new ArrayList<>();
        for (final TreePath pathNode : clipboard) {
            nodeList.add((DefaultMutableTreeNode) pathNode.getLastPathComponent());
        }
        final DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) srcPath.getLastPathComponent();
        fileSystemTree.removeNodes(rootNode, nodeList);
        fileSystemTree.reloadTreeByNode(rootNode);
    }


    @Override
    public void mkDir(final TreePath srcPath, final String name) throws IOException {
        final File f = new File(this.getfileFromTP(srcPath).getAbsolutePath() + System.getProperty("file.separator") + name);
        f.mkdir();

        final DefaultMutableTreeNode srcNode = (DefaultMutableTreeNode) srcPath.getLastPathComponent();
        fileSystemTree.addNodesToTree(srcNode, new ArrayList<File>(Arrays.asList(f)));
        fileSystemTree.reloadTreeByNode((DefaultMutableTreeNode) srcPath.getLastPathComponent());
    }


    @Override
    public void mkFile(final TreePath srcPath, final String name) throws IOException {
        final File f = new File(this.getfileFromTP(srcPath).getAbsolutePath() + System.getProperty("file.separator") + name);
        f.createNewFile();

        final DefaultMutableTreeNode srcNode = (DefaultMutableTreeNode) srcPath.getLastPathComponent();
        fileSystemTree.addNodesToTree(srcNode, Arrays.asList(f));
        fileSystemTree.reloadTreeByNode((DefaultMutableTreeNode) srcPath.getLastPathComponent());
    }


    @Override
    public void mkLink(final TreePath srcTarget, final TreePath srcLink, final String name) throws IOException {
        final DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) srcTarget.getLastPathComponent();
        final DefaultMutableTreeNode linkNode = (DefaultMutableTreeNode) srcLink.getLastPathComponent();

        final Node target = (Node) targetNode.getUserObject();
        final Node link = (Node) linkNode.getUserObject();

        final Path targetPath = Paths.get(target.getFile().getAbsolutePath());
        final File fileLink = new File(link.getFile().getAbsolutePath() + System.getProperty("file.separator") + name);
        final Path linkPath = Paths.get(fileLink.getAbsolutePath());

        Files.createSymbolicLink(linkPath, targetPath);
        fileSystemTree.addNodesToTree(targetNode, Arrays.asList(fileLink));
        fileSystemTree.reloadTreeByNode(targetNode);
    }

//    @Override
//    public void update(final TreePath dirPath, final TreePath newFilePath, final TreePath oldFilePath) {
//    	final DefaultMutableTreeNode dirNode = (DefaultMutableTreeNode) dirPath.getLastPathComponent();
//    	final DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) newFilePath.getLastPathComponent();
//    	final DefaultMutableTreeNode oldNode = (DefaultMutableTreeNode) oldFilePath.getLastPathComponent();
//
//    	fileSystemTree.removeNodes(dirNode, Arrays.asList(oldNode));
//    	fileSystemTree.
//    	/*
//        final DefaultMutableTreeNode dirNode = (DefaultMutableTreeNode) dirPath.getLastPathComponent();
//        final DefaultMutableTreeNode fileNode = (DefaultMutableTreeNode) newFilePath.getLastPathComponent();
//        final Node node = (Node) fileNode.getUserObject();
//
//        fileSystemTree.removeNodes(dirNode, Arrays.asList(fileNode));
//        fileSystemTree.addNodesToTree(dirNode, Arrays.asList(node.getFile()));
//        fileSystemTree.reloadTreeByNode(dirNode);
//        */
//    }
}
