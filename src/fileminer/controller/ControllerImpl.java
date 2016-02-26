package fileminer.controller;

import java.io.IOException;

import fileminer.main.FileMinerLogger;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
import fileminer.model.FileSystemTreeImpl;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {
    private FileMinerGUI view;
    private FileSystemTreeImpl fst;
    private final FileMinerLogger logger;

    /**
     * ControllerImpl constructor:
     *          I create the view and the model.
     */
    public ControllerImpl() {
        this.logger = FileMinerLogger.getInstance();
        this.fst = new FileSystemTreeImpl();
        this.view = new FileMinerGUI(this);
    }

    @Override
    public void invokesCommand(final Commands command) {

        final FileOperations operation = new FileOperationsImpl();
        final Clipboard clipboard = new ClipboardImpl();
        
        switch (command) {
        case COPY:
            clipboard.addPathFiles("");
            operation.copy("");
            break;

        case PASTE:
            try {
                clipboard.getPathFiles();
                operation.pasteTo("");
                clipboard.clean();
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case CUT:
            break;

        case MOVE:
            //operation.moveTo(srcPath, destPath, createDestDir);
            break;

        case LINK:
            break;

        case DELETE:
            try {
                operation.remove("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case NEW:
            break;

        case OPEN:
            try {
                operation.open("");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case MODIFY:
            break;

        default: break;
        }

        //        if (Commands.COPY.toString().equals(command)) {
        //            operation.copy(srcPath);
        //        } else if (Commands.PASTE.toString().equals(command)) {
        //            try {
        //                operation.pasteTo(destPath);
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        } else if (Commands.CUT.toString().equals(command)) {
        //
        //        } else if (Commands.MOVE.toString().equals(command)) {
        //            /*try {
        //                        operation.moveTo(srcPath, destPath);
        //                    } catch (IOException e) {
        //                        e.printStackTrace();
        //                    }*/
        //        } else if (Commands.LINK.toString().equals(command)) {
        //            /*link*/
        //        } else if (Commands.DELETE.toString().equals(command)) {
        //            try {
        //                operation.remove(srcPath);
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        } else if (Commands.NEW.toString().equals(command)) {
        //            /*new*/
        //        } else if (Commands.MODIFY.toString().equals(command)) {
        //            /*modify*/
        //        } else if (Commands.OPEN.toString().equals(command)) {
        //            try {
        //                operation.open(srcPath);
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        }
    }

    @Override
    public FileSystemTreeImpl getFileSystem() {
        return this.fst;
    }

    @Override
    public void printOSInfo() {
        logger.getConsole().putString(new OSPropertiesImpl().toString());
    }

    @Override
    public void quit() {
        this.fst = null;
        this.view = null;
        System.exit(0);
    }

}
