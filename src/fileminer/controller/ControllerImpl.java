package fileminer.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.archiver.Archiver;
import fileminer.model.archiver.ArchiverZIP;
import fileminer.view.FileMinerGUI;
import net.lingala.zip4j.exception.ZipException;
/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {
    private final FileMinerGUI view;
    private FileSystemTreeImpl fst;
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
        final Archiver archiver;

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
                    this.updateTree();
                }
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case CUT:              
            this.clipboard.addPathFiles(this.view.getSelectedItems()); 
            this.clipboard.setParameter(false);
            this.operation.copy(this.clipboard.getPathFiles());
            break;

        case LINK:
            try {
                final String srcDest = this.view.newObjectName("Inserire indirizzo del file che si vuole collegare:");
                if (srcDest != null) {
                    final String name = this.view.newObjectName("Inserire nome collegamento:");
                    if (name != null) {
                        this.operation.mkLink(srcDest, this.view.getCurrentDir(), name);
                        this.updateTree();
                    }
                }
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case DELETE:
            try {
                this.clipboard.addPathFiles(this.view.getSelectedItems());
                this.operation.remove(this.clipboard.getPathFiles());
                this.clipboard.clean();
                this.updateTree();
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case NEW:
            try {
                final int option = this.view.newObjectType();
                /*
                 * 0 = directory
                 * 1 = file
                 * altro = ha chiuso la finestra annullando l'operazione
                 */
                if (option == 0) {
                    final String name = this.view.newObjectName("Inserire nome della nuova directory:");
                    if (name != null) {
                        System.out.println("non null");
                        this.operation.mkDir(this.view.getCurrentDir(), name);
                        this.updateTree();
                    }
                } else if (option == 1) {
                    final String name = this.view.newObjectName("Inserire nome del nuovo file:");
                    if (name != null) {
                        this.operation.mkFile(this.view.getCurrentDir(), name);
                        this.updateTree();
                    }
                }
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case OPEN:
            try {
                this.operation.open(this.view.getSelectedItems());
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case COMPRESS:            
            archiver = new ArchiverZIP();
            try {
                final String name = this.view.newObjectName("Inserire nome dell'archivio:");
                archiver.compress(view.getSelectedItems(), name, view.getCurrentDir());
            } catch (FileNotFoundException | ZipException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            this.updateTree();
            break;

        case DECOMPRESS: 
            archiver = new ArchiverZIP();
            try {
                archiver.decompress(view.getSelectedItems(), view.getCurrentDir());
            } catch (ZipException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            } catch (FileNotFoundException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            this.updateTree();
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

    private void updateTree() {
        this.fst = new FileSystemTreeImpl();
        this.view.updateTree(this.fst.getTree());
        this.view.repaintGUI();
    }
}
