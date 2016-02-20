package fileminer.controller;

import javax.swing.SwingUtilities;

import fileminer.model.FileBrowser;
import fileminer.model.FileBrowserImpl;
import fileminer.view.FileMinerGUI;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ControllerImpl implements Controller {

    @Override
    public void initializesGUI() {
        final FileBrowser root = new FileBrowserImpl();

        //creo la gui e gli passo l'albero della root
        final FileMinerGUI gui = new FileMinerGUI(root.getTree());
        
        //faccio partire la gui
        gui.start();
    }

    @Override
    public void invokesCommand(final String command) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*
                 * uso la programmazione concorrente per avviare le operazioni di
                 * copia, incolla, taglia, sposta, cancella, link, crea... ecc ecc
                 */
                
                if (Commands.COPY.toString().equals(command)) {
                    /*copy*/
                } else if (Commands.PASTE.toString().equals(command)) {
                    /*paste*/
                } else if (Commands.CUT.toString().equals(command)) {
                    /*cut*/
                } else if (Commands.MOVE.toString().equals(command)) {
                    /*move*/
                } else if (Commands.LINK.toString().equals(command)) {
                    /*link*/
                } else if (Commands.DELETE.toString().equals(command)) {
                    /*delete*/
                } else if (Commands.NEW.toString().equals(command)) {
                    /*new*/
                } else if (Commands.MODIFY.toString().equals(command)) {
                    /*modify*/
                }
            }
        });
    }

}
