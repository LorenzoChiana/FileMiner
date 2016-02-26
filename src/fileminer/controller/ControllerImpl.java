package fileminer.controller;

import java.io.IOException;
import java.util.ArrayList;

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
    private final FileMinerGUI view;
    private final FileSystemTreeImpl fst;
    private final FileMinerLogger logger;
    private final Clipboard clipboard;

    /**
     * ControllerImpl constructor:
     *          I create the view and the model.
     */
    public ControllerImpl() {
        this.logger = FileMinerLogger.getInstance();
        this.fst = new FileSystemTreeImpl();
        this.view = new FileMinerGUI(this);
        this.clipboard = new ClipboardImpl();
    }

    @Override
    public void invokesCommand(final Commands command) {

        final FileOperations operation = new FileOperationsImpl();
        
        switch (command) {
        case COPY:
            this.clipboard.addPathFiles(new ArrayList<>()); //richiamo metodo view che mi passa la lista di path
            //operation.copy(this.clipboard.getPathFiles());
            break;

        case PASTE:
            try {
                operation.pasteTo("richiamo metodo della view che mi restituisce la path della directory su cui copiare");
                this.clipboard.clean();
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
            
        case COMPRESS:
            break;
        
        case DECOMPRESS: 
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
    public String getOSInfo() {
        return new OSPropertiesImpl().toString();
    }

    @Override
    public void quit() {
        System.exit(0);
    }
}
