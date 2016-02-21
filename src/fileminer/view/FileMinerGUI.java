package fileminer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import fileminer.controller.Controller;
import fileminer.controller.ControllerImpl;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI extends JFrame {

    private static final long serialVersionUID = -3479742762830497941L;
    private static final double SCREENRATIO = 1.5;

    private Controller observer;

    public FileMinerGUI(final DefaultMutableTreeNode roots) {
        super();
        initializeFrame();
        createComponents();
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
        add(toolbar, BorderLayout.NORTH);

        final JScrollPane treeView = new JScrollPane(new TreeExplorerPanel());
        treeView.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
        add(treeView, BorderLayout.WEST);
    }
    
    /**
     * Display the frame.
     */
    public void start() {
        setVisible(true);
    }

    private void setObserver() {
        
    }

    private void exitProcedure() {
        dispose();
        System.exit(0);
    } 
}
