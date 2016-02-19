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
    public void inizializeGUI() {
        /* new classe InfoSistema (?) */
        final FileBrowser root = new FileBrowserImpl();
        root.setTree();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileMinerGUI(/* istanza InfoSistema */);
            }
        });
    }

}
