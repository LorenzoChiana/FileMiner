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
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //creo la gui e gli passo l'albero della root
                final FileMinerGUI gui = new FileMinerGUI(/* root.setTree() */);
                //faccio partire la gui
                gui.start();
            }
        });
    }

}
