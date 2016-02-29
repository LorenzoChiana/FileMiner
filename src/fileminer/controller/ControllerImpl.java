package fileminer.controller;

import java.awt.Desktop;
import java.io.IOException;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.archiver.ArchiverZIP;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {

    private final FileMinerGUI view;
    private final FileSystemTreeImpl fst;
    private final FileMinerLogger logger;
    private final Clipboard clipboard;
    private final FileOperations operation;
    private final ArchiverZIP archiver;
    private final Chronology chronology;

    /**
     * ControllerImpl constructor:
     *          I create the view and the model.
     */
    public ControllerImpl() {
        this.fst = new FileSystemTreeImpl();
        this.view = new FileMinerGUI(this);
        this.logger = FileMinerLogger.getInstance();
        this.clipboard = new ClipboardImpl();
        this.chronology = new ChronologyImpl();
        
        this.operation = new FileOperationsImpl(fst);
        this.archiver = new ArchiverZIP();
    }

    @Override
    public void invokesCommand(final Commands command) {
        switch (command) {
        case BACK:
            this.chronology.prevDir();
            this.view.updateNodesTable(this.chronology.getCurrentDirectory());
            break;

        case NEXT:
            this.chronology.nextDir();
            this.view.updateNodesTable(this.chronology.getCurrentDirectory());
            break;

        case COPY:
            if (!this.view.getSelectedItems().isEmpty()) {
                this.clipboard.addPathFiles(this.view.getSelectedItems());
                this.clipboard.setParameter(true);
                logger.getConsole().putString(this.clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                logger.getConsole().putString("Nothing to copy...");
            }
            break;

        case PASTE:
            try {
                if (!this.clipboard.isEmpty()) {

                    this.operation.pasteTo(this.clipboard.getPathFiles(),
                                           this.view.getCurrentDir(),
                                           this.clipboard.getParameter());

                    this.clipboard.clean();
                    this.view.updateNodesTable(this.view.getCurrentDir());

                } else {
                    logger.getConsole().putString("Nothing to paste...");
                }
            } catch (IOException e) {
                logger.getConsole().putString(e.getMessage());
            }
            break;

        case CUT:
            if (!this.view.getSelectedItems().isEmpty()) {
                this.clipboard.addPathFiles(this.view.getSelectedItems()); 
                this.clipboard.setParameter(false);
                logger.getConsole().putString(this.clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                logger.getConsole().putString("Nothing to cut...");
            }
            break;

        case LINK:
            try {
                if (!this.clipboard.isEmpty()) {
                    this.operation.mkLink(this.view.getSelectedItems().get(0),
                                          this.view.getCurrentDir(),
                                          "google");
                } else {
                    logger.getConsole().putString("Nessun elemento selezionato");
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

            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }
            break;

        case NEW:
            try {
                this.operation.mkDir(this.view.getCurrentDir(), "");
            } catch (IOException e) {
                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
            }

            break;

        case OPEN:
            try {
                if (Desktop.isDesktopSupported()) {
                    this.operation.open(this.view.getSelectedItems());
                } else {
                    logger.getConsole().putString("Desktop mode not supported!");
                }
            } catch (IOException e) {
                logger.getConsole().putString(e.getMessage());
            }
            break;

        case COMPRESS:

//            try {
//                final String name = this.view.newObjectName("Inserire nome dell'archivio:");
//                archiver.compress(view.getSelectedItems(), name, view.getCurrentDir());
//            } catch (FileNotFoundException | ZipException e) {
//                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
//            }

            break;

        case DECOMPRESS: 
//            try {
//                archiver.decompress(view.getSelectedItems(), view.getCurrentDir());
//            } catch (ZipException e) {
//                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
//            } catch (FileNotFoundException e) {
//                FileMinerLogger.getInstance().getConsole().putString(e.getMessage());
//            }

            break;

        default: break;
        }
    }

    @Override
    public FileSystemTreeImpl getFileSystem() {
        return this.fst;
    }

    public Chronology getChronology() {
        return this.chronology;
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
