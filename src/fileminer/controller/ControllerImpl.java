package fileminer.controller;

import java.io.File;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.model.FileSystemTree;
import fileminer.model.FileSystemTreeImpl;
import fileminer.model.FileOperations;
import fileminer.model.FileOperationsImpl;
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
    public void invokesCommand(final String command) {
        
        final FileOperations operation = new FileOperationsImpl();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*
                 * uso la programmazione concorrente per avviare le operazioni di
                 * copia, incolla, taglia, sposta, cancella, link, crea... ecc ecc
                 */
                
                if (Commands.COPY.toString().equals(command)) {
                    operation.copy(new File("")); /*cambiare new file con effettivo file*/
                } else if (Commands.PASTE.toString().equals(command)) {
                    operation.pasteTo("");
                } else if (Commands.CUT.toString().equals(command)) {
                    operation.cut();
                } else if (Commands.MOVE.toString().equals(command)) {
                    operation.moveTo("");
                } else if (Commands.LINK.toString().equals(command)) {
                    /*link*/
                } else if (Commands.DELETE.toString().equals(command)) {
                    operation.remove();
                } else if (Commands.NEW.toString().equals(command)) {
                    /*new*/
                } else if (Commands.MODIFY.toString().equals(command)) {
                    /*modify*/
                }
            }
        });
    }

    @Override
    public DefaultMutableTreeNode getFileSystemTree() {
        return new FileSystemTreeImpl().getTree();
    }

}
