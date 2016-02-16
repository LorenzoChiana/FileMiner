package fileminer.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI extends JFrame {

    private static final long serialVersionUID = -3479742762830497941L;
    private static final double SCREENRATIO = 1.5;

    // private final InfoSistema infoSys

    public FileMinerGUI(/* istanza classe InfoSistema arg */) {
        super();
        // this.infoSys = arg;
        initializeFrame();
        createComponents();
        this.setVisible(true);
    }

    private void initializeFrame() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("FileMiner v1.0");
        this.setLocationByPlatform(true);
        this.setSize((int) (screenSize.getWidth() / SCREENRATIO), (int) (screenSize.getHeight() / SCREENRATIO));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent event) {
                exitProcedure();
            }
        });
        this.setIconImage(new ImageIcon(getClass().getResource("/images/Logo32.png")).getImage());
        this.getContentPane().setLayout(new BorderLayout());
    }

    private void createComponents() {
        final UpperToolbar toolbar = new UpperToolbar(/* infoSys.listaComandi */);
        toolbar.addMouseListener(new MouseAdapter() {
            /* robe dal model */
        });

        this.add(toolbar, BorderLayout.NORTH);

        final JScrollPane treeView = new JScrollPane(new TreeExplorerPanel(/* infoSys.getRoots */));
        treeView.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
        treeView.addMouseListener(new MouseAdapter() {
            /* robe dal model */
        });
        this.add(treeView, BorderLayout.WEST);
    }

    private void exitProcedure() {
        this.dispose();
        System.exit(0);
    } 
}
