package fileminer.controller;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.archiver.ArchiverZIP;
import fileminer.view.FileMinerGUI;
import net.lingala.zip4j.exception.ZipException;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {

    private static final int DIALOG_FILE = 0;
    private static final int DIALOG_DIR = 1;
    private static final int DIALOG_LINK = 2;
    private static final int DIALOG_ARCHIVER = 3;
    private final FileMinerGUI view;
    private final FileSystemTreeImpl fst;
    private final FileMinerLogger logger;
    private final Clipboard clipboard;
    private final FileOperations operation;
    private ArchiverZIP archiver;
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
                this.logger.getConsole().putStringLater(this.clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                this.logger.getConsole().putStringLater("Nothing to copy...");
            }
            break;

        case PASTE:
            try {
                if (!this.clipboard.isEmpty()) {
                    this.operation.pasteTo(this.clipboard.getPathFiles(), this.view.getCurrentDir(), this.clipboard.getParameter());
                    this.clipboard.clean();
                    this.view.updateNodesTable(this.view.getCurrentDir());
                } else {
                    this.logger.getConsole().putStringLater("Nothing to paste...");
                }
            } catch (IOException e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case CUT:
            if (!this.view.getSelectedItems().isEmpty()) {
                this.clipboard.addPathFiles(this.view.getSelectedItems()); 
                this.clipboard.setParameter(false);
                this.logger.getConsole().putStringLater(this.clipboard.getPathFiles().size() + " elemento/i nella clipboard");
            } else {
                this.logger.getConsole().putStringLater("Nothing to cut...");
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
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_FILE:
            try {
                final String name = this.view.getDialogString(DIALOG_FILE);
                if (name != null) {
                    this.operation.mkFile(this.view.getCurrentDir(), name);
                } else {
                    this.logger.getConsole().putStringLater("Invalid name!");
                    this.view.updateNodesTable(this.view.getCurrentDir());
                }
            } catch (Exception e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_DIR:
            try {
                final String name = this.view.getDialogString(DIALOG_DIR);
                if (name != null) {
                    this.operation.mkDir(this.view.getCurrentDir(), name);
                    this.view.updateNodesTable(this.view.getCurrentDir());
                } else {
                    this.logger.getConsole().putStringLater("Invalid name!");
                }
            } catch (Exception e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case NEW_LINK:
            try {
                final String name = this.view.getDialogString(DIALOG_LINK);
                if (name != null && this.view.getSelectedItems().size() == 1) {
                    this.operation.mkLink(this.view.getCurrentDir(), this.view.getSelectedItems().get(0), name);
                    this.view.updateNodesTable(this.view.getCurrentDir());
                } else {
                    this.logger.getConsole().putStringLater("Invalid selection or name!");
                }
            } catch (Exception e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case OPEN:
            try {
                if (Desktop.isDesktopSupported()) {
                    this.operation.open(this.view.getSelectedItems());
                } else {
                    this.logger.getConsole().putStringLater("Desktop mode not supported!");
                }
            } catch (Exception e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case COMPRESS:
            
            this.archiver = new ArchiverZIP();
            String name = this.view.getDialogString(DIALOG_ARCHIVER);
            
            try {
                this.archiver.compress(this.view.getSelectedItems(), name, this.view.getCurrentDir());
            } catch (FileNotFoundException | ZipException e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        case DECOMPRESS: 
            
            try {
                this.archiver.decompress(this.view.getSelectedItems(), this.view.getCurrentDir());
            } catch (FileNotFoundException | ZipException e) {
                this.logger.getConsole().putStringLater(e.getMessage());
            }
            break;

        default: break;
        }
    }

    @Override
    public FileSystemTreeImpl getFileSystem() {
        return this.fst;
    }

    @Override
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
