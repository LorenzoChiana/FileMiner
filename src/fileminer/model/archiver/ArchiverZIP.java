package fileminer.model.archiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.main.FileMinerLogger;
import fileminer.model.Node;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * @author Daniele Gambaletta
 * Compress file in .zip
 */
public class ArchiverZIP implements Archiver {

    private static final String EXTENSION_ZIP = ".zip";

    private final FileMinerLogger logger;

    public ArchiverZIP() {
        this.logger = FileMinerLogger.getInstance();
    }

    @Override
    public void compress(final List<TreePath> paths, final String name, final TreePath dest) throws FileNotFoundException, ZipException {

        List<File> files = new ArrayList<File>(); 

        for (final TreePath path : paths) {
            final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            final Node node = (Node) treeNode.getUserObject();
            files.add(node.getFile());
        }

        final DefaultMutableTreeNode treeDest = (DefaultMutableTreeNode) dest.getLastPathComponent();
        final Node nodeDest = (Node) treeDest.getUserObject();
        ZipFile zipFile = new ZipFile(nodeDest.getFile() + System.getProperty("file.separator") + name + EXTENSION_ZIP);

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(false);

        int n = 1;
        int size = files.size();
        for (final File f : files) {
            logger.getConsole().putStringLater("Compression of " + n + " element out of " + size + " in progress...");
            if (f.isDirectory()) {
                zipFile.addFolder(f.getAbsolutePath(), parameters);
            } else {
                zipFile.addFile(f, parameters);
            }
            n++;
        }
    }

    @Override
    public void decompress(final List<TreePath> archives, final TreePath destPath) throws FileNotFoundException, ZipException {
        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) destPath.getLastPathComponent();
        final Node destNode = (Node) treeNode.getUserObject();

        int n = 1;
        int size = archives.size();
        for (final TreePath archivePath: archives) {
            logger.getConsole().putStringLater("Decompression of " + n + " element out of " + size + " in progress...");
            final DefaultMutableTreeNode treeNode2 = (DefaultMutableTreeNode) archivePath.getLastPathComponent();
            final Node archiveNode = (Node) treeNode2.getUserObject();
            ZipFile zipFile = new ZipFile(archiveNode.getFile());
            zipFile.extractAll(destNode.getFileName());
            n++;
        }

    }


}
