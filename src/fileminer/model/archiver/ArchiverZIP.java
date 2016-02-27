package fileminer.model.archiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void compress(final List<String> paths, final String name, final String dest) throws FileNotFoundException, ZipException {

        List<File> files = new ArrayList<File>(); 

        for (String path : paths) {
            files.add(new File(path));
        }

        ZipFile zipFile = new ZipFile(dest + System.getProperty("file.separator") + name + EXTENSION_ZIP);

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(false);

        for (File f : files) {
            if (f.isDirectory()) {
                zipFile.addFolder(f.getAbsolutePath(), parameters);
            } else {
                zipFile.addFile(f, parameters);
            }
        }
    }

    @Override
    public void decompress(final List<String> archives, final String dest) throws FileNotFoundException, ZipException {
        for (String archive: archives) {
            ZipFile zipFile = new ZipFile(archive);
            zipFile.extractAll(dest);
        }

    }


}
