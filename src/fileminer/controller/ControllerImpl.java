package fileminer.controller;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

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

    /**
     * ControllerImpl constructor:
     *          I create the view and the model.
     */
    public ControllerImpl() {
        this.view = new FileMinerGUI();
    }

    @Override
    public void invokesCommand(final Commands command, final String srcPath, final String destPath) {

        final FileOperations operation = new FileOperationsImpl();

        switch(command) {
        case COPY: {
            operation.copy(srcPath);
            break;
        }
        case PASTE: {
            try {
                operation.pasteTo(destPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        case CUT: {
            break;
        }
        case MOVE: {
            //operation.moveTo(srcPath, destPath, createDestDir);
            break;
        }
        case LINK: {
            break;
        }
        case DELETE: {
            try {
                operation.remove(srcPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        case NEW: {
            break;
        }
        case OPEN: {
            try {
                operation.open(srcPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        case MODIFY: {
            break;
        }
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
    public DefaultMutableTreeNode getFileSystemTree() {
        return new FileSystemTreeImpl().getTree();
    }

}
