package fileminer.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.archiver.Archiver;
import fileminer.model.archiver.ArchiverZIP;
import fileminer.view.FileMinerGUI;
import java.util.List;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {
    private final FileMinerGUI view;
    private final FileSystemTreeImpl fst;
    private final Clipboard clipboard;
    private final FileOperations operation;

    /**
     * ControllerImpl constructor:
     *          I create the view and the model.
     */
    public ControllerImpl() {
        this.fst = new FileSystemTreeImpl();
        this.view = new FileMinerGUI(this);
        this.clipboard = new ClipboardImpl();
        this.operation = new FileOperationsImpl();
    }

    @Override
    public void invokesCommand(final Commands command) {
        List<String> l = new ArrayList<>();
        File file;
        switch (command) {
        case COPY:       
            this.clipboard.addPathFiles(this.view.getSelectedItems());
            this.clipboard.setParameter(true);
            this.operation.copy(this.clipboard.getPathFiles());
            break;

        case PASTE:
            try {
                if (!this.clipboard.isEmpty()) {
                    this.operation.pasteTo(this.view.getCurrentDir(), this.clipboard.getParameter());
                    this.clipboard.clean();
                    this.fst.getTree().reload();
                    this.view.updateTree(this.fst.getTree());
                    this.view.repaintGUI();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case CUT:              
            this.clipboard.addPathFiles(this.view.getSelectedItems()); 
            this.clipboard.setParameter(false);
            this.operation.copy(this.clipboard.getPathFiles());
            break;

        case LINK:
            try {
                this.operation.mkLink("/home/lorenzo/Immagini/aa.jpg", "/home/lorenzo/Documenti", "google");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.fst.getTree().reload();
            break;

        case DELETE:
            try {
                this.clipboard.addPathFiles(this.view.getSelectedItems());
                this.operation.remove(this.clipboard.getPathFiles());
                this.clipboard.clean();
                this.fst.getTree().reload();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case NEW:
            try {
                this.operation.mkDir("", "");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.fst.getTree().reload();
            break;

        case OPEN:
            try {
                this.operation.open("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case COMPRESS:
            /*file = new File("/home/lorenzo/Immagini/Prova/1.txt");
            l.add(file.getAbsolutePath());
            final Archiver archiver = new ArchiverZIP();
            archiver.compress(l, "archivio", "/home/lorenzo/Documenti");*/
            this.fst.getTree().reload();
            break;

        case DECOMPRESS: 
            /*final Archiver archiver = new ArchiverZIP();
            archiver.decompress("/home/lorenzo/Documenti/archivio.zip", "/home/lorenzo/Documenti");*/
            this.fst.getTree().reload();
            break;

        default: break;
        }
    }

    @Override
    public FileSystemTreeImpl getFileSystem() {
        return this.fst;
    }

    @Override
    public String getOSInfo() {
        return new OSPropertiesImpl().toString();
    }

    @Override
    public void quit() {
        System.exit(0);
    }
}
