package fileminer.Controller;

import javax.swing.SwingUtilities;
import fileminer.View.FileMinerGUI;

public class FileMinerApp {

    public static void main(String[] args) {

        /* new classe InfoSistema (?) */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileMinerGUI(/* istanza InfoSistema */);
            }
        });
    }
}
