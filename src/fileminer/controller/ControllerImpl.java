package fileminer.controller;

import java.io.File;

import javax.swing.SwingUtilities;

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

    @Override
    public void initializesGUI() {
        final FileSystemTree root = new FileSystemTreeImpl();

        //creo la gui e gli passo l'albero della root
        final FileMinerGUI gui = new FileMinerGUI(/* root.getTree() */);
        
        //faccio partire la gui
        gui.start();
    }

    @Override
    public void invokesCommand(final String command) {
        
        final FileOperations model = new FileOperationsImpl();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*
                 * uso la programmazione concorrente per avviare le operazioni di
                 * copia, incolla, taglia, sposta, cancella, link, crea... ecc ecc
                 */
                
                if (Commands.COPY.toString().equals(command)) {
                    model.copy(model.getFileSrc()); /*cambiare new file con effettivo file*/
                } else if (Commands.PASTE.toString().equals(command)) {
                    model.pasteTo("");
                } else if (Commands.CUT.toString().equals(command)) {
                    model.cut();
                } else if (Commands.MOVE.toString().equals(command)) {
                    model.moveTo("");
                } else if (Commands.LINK.toString().equals(command)) {
                    /*link*/
                } else if (Commands.DELETE.toString().equals(command)) {
                    model.remove();
                } else if (Commands.NEW.toString().equals(command)) {
                    /*new*/
                } else if (Commands.MODIFY.toString().equals(command)) {
                    /*modify*/
                }
            }
        });
    }

}
