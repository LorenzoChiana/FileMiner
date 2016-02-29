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
            chronology.prevDir();
            view.updateNodesTable(chronology.getCurrentDirectory());
            break;

        case NEXT:
            chronology.nextDir();
            view.updateNodesTable(chronology.getCurrentDirectory());
            break;

        case COPY:
            if (!view.getSelectedItems().isEmpty()) {
                clipboard.addPathFiles(view.getSelectedItems());
                clipboard.setParameter(true);
                logger.getConsole().putStringLater(clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                logger.getConsole().putStringLater("Nothing to copy...");
            }
            break;

        case PASTE:
            try {
                if (!clipboard.isEmpty()) {
                    operation.pasteTo(clipboard.getPathFiles(), view.getCurrentDir(), clipboard.getParameter());
                    clipboard.clean();
                    view.updateNodesTable(view.getCurrentDir());
                } else {
                    logger.getConsole().putStringLater("Nothing to paste...");
                }
            } catch (IOException e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case CUT:
            if (!view.getSelectedItems().isEmpty()) {
                clipboard.addPathFiles(view.getSelectedItems()); 
                clipboard.setParameter(false);
                logger.getConsole().putStringLater(clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                logger.getConsole().putStringLater("Nothing to cut...");
            }
            break;

        case DELETE:
            try {
                final boolean result = view.getConfirmDialog();
                if (result) {
                    this.operation.remove(this.view.getSelectedItems(), this.view.getCurrentDir());
                    this.view.updateNodesTable(this.view.getCurrentDir());
                }
            } catch (IOException e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_FILE:
            try {
                final String name = view.getDialogString(0);
                if (name != null) {
                    operation.mkFile(view.getCurrentDir(), name);
                } else {
                    logger.getConsole().putStringLater("Invalid name!");
                    view.updateNodesTable(view.getCurrentDir());
                }
            } catch (Exception e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_DIR:
            try {
                final String name = view.getDialogString(1);
                if (name != null) {
                    operation.mkDir(view.getCurrentDir(), name);
                    view.updateNodesTable(view.getCurrentDir());
                } else {
                    logger.getConsole().putStringLater("Invalid name!");
                }
            } catch (Exception e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_LINK:
            try {
                final String name = view.getDialogString(2);
                if (name != null && view.getSelectedItems().size() == 1) {
                    operation.mkLink(view.getCurrentDir(), view.getSelectedItems().get(0), name);
                    view.updateNodesTable(view.getCurrentDir());
                } else {
                    logger.getConsole().putStringLater("Invalid selection or name!");
                }
            } catch (Exception e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case OPEN:
            try {
                if (Desktop.isDesktopSupported()) {
                    operation.open(view.getSelectedItems());
                } else {
                    logger.getConsole().putStringLater("Desktop mode not supported!");
                }
            } catch (Exception e) {
                logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case COMPRESS:

//            try {
//                final String name = view.newObjectName("Inserire nome dell'archivio:");
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
        return fst;
    }

    public Chronology getChronology() {
        return chronology;
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
