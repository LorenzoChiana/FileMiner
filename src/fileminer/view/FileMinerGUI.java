package fileminer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import fileminer.controller.Controller;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI extends JFrame {

    private static final long serialVersionUID = -3479742762830497941L;
    private static final double SCREENRATIO = 1.5;

    private final Controller controller;

    private final SplashScreen splashScreen;

    /**
     * Constructor of FileMinerGUI.
     * @param ctrl 
     */
    public FileMinerGUI(final Controller ctrl) {
        super();
        controller = ctrl;

        splashScreen = new SplashScreen("/images/Logo256.png");
        splashScreen.setVisible(true);
        
        initializeFrame();
        createComponents();

        splashScreen.closeSplash();
        setVisible(true);
    }

    private void initializeFrame() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("FileMiner v1.0");
        setLocationByPlatform(true);
        setSize((int) (screenSize.getWidth() / SCREENRATIO), (int) (screenSize.getHeight() / SCREENRATIO));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent event) {
                exitProcedure();
            }
        });

        setIconImage(new ImageIcon(getClass().getResource("/images/Logo32.png")).getImage());
        getContentPane().setLayout(new BorderLayout());
    }

    private void createComponents() {
        final UpperToolbar toolbar = new UpperToolbar();
        getContentPane().add(toolbar, BorderLayout.NORTH);

        final JScrollPane treeView = new JScrollPane(new TreeExplorerPanel(controller.getFileSystemTree()));
        treeView.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
        getContentPane().add(treeView, BorderLayout.WEST);
    }

    private void exitProcedure() {
        dispose();
        System.exit(0);
    }
}
