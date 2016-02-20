package fileminer.Controller;

import javax.swing.SwingUtilities;

import fileminer.Model.FileBrowser;
import fileminer.Model.FileBrowserImpl;
import fileminer.View.FileMinerGUI;

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
                final FileMinerGUI gui = new FileMinerGUI(root.setTree());
                //faccio partire la gui
                gui.start();
            }
        });
    }

}
