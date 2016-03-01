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
 * @author Daniele Gambaletta
 * Class for various operations on files.
 */
public class FileOperationsImpl implements FileOperations {

    private final FileSystemTreeImpl fileSystemTree;
    private final FileMinerLogger logger;

    /**
     * Initialization.
     * @param fst FileSystemTreeImpl
     */
    public FileOperationsImpl(final FileSystemTreeImpl fst) {
        this.fileSystemTree = fst;
        this.logger = FileMinerLogger.getInstance();
    }

    /**
     * Add files from tree paths.
     * @param treePaths list of treepath
     * @return files list
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
     * Add file from tree path.
     * @param treePath
     * @return file
     */
    private File getfileFromTP(final TreePath treePath) {
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

        if (isCopy) { // Incolla di una Copia
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
            // Aggiorna albero
            fileSystemTree.addNodesToTree(destNode, files);
        } else { // Incolla di un Taglia

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
            // Aggiorna albero
            fileSystemTree.moveNodes(destNode, oldNodes);
            fileSystemTree.reloadTreeByNode(oldParent);
        }
        // Aggiorna albero
        fileSystemTree.reloadTreeByNode(destNode);
    }


    @Override
    public void open(final List<TreePath> clipboard) throws IOException {
        // Apri files
        for (final TreePath filePath : clipboard) {
            final File file = this.getfileFromTP(filePath);
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            }
        }
    }


    @Override
    public void remove(final List<TreePath> clipboard, final TreePath srcPath) throws IOException {
        final List<File> files = getfileFromTP(clipboard);

        // Rimuovi files
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

        // Aggiorna albero
        fileSystemTree.removeNodes(rootNode, nodeList);
        fileSystemTree.reloadTreeByNode(rootNode);
    }


    @Override
    public void mkDir(final TreePath srcPath, final String name) throws IOException {
        final File f = new File(this.getfileFromTP(srcPath).getAbsolutePath() + System.getProperty("file.separator") + name);
        
        // Crea cartella
        f.mkdir();

        final DefaultMutableTreeNode srcNode = (DefaultMutableTreeNode) srcPath.getLastPathComponent();

        // Aggiorna albero
        fileSystemTree.addNodesToTree(srcNode, new ArrayList<File>(Arrays.asList(f)));
        fileSystemTree.reloadTreeByNode((DefaultMutableTreeNode) srcPath.getLastPathComponent());
    }


    @Override
    public void mkFile(final TreePath srcPath, final String name) throws IOException {
        final File f = new File(this.getfileFromTP(srcPath).getAbsolutePath() + System.getProperty("file.separator") + name);
        
        // Crea file
        f.createNewFile();

        final DefaultMutableTreeNode srcNode = (DefaultMutableTreeNode) srcPath.getLastPathComponent();

        // Aggiorna albero
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

        // Crea link
        Files.createSymbolicLink(linkPath, targetPath);

        // Aggiorna albero
        fileSystemTree.addNodesToTree(targetNode, Arrays.asList(fileLink));
        fileSystemTree.reloadTreeByNode(targetNode);
    }

}
