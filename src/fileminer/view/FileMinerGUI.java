package fileminer.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import fileminer.controller.Controller;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI extends JFrame implements PropertyChangeListener {

    private static final long serialVersionUID = -3479742762830497941L;
    private static final double SCREENRATIO = 1.5;

    private final Controller controller;

    private final SplashScreen splashScreen;

    private JScrollPane treeView;

    private JSplitPane splitPane;

    private NodeContentPanel ncp;

    private InformationPanel infoPanel;

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
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        exitProcedure();
                    }
                    
                });
            }
        });

        setIconImage(new ImageIcon(getClass().getResource("/images/Logo32.png")).getImage());
        getContentPane().setLayout(new BorderLayout());
    }

    private void createComponents() {
        // TOOL PANEL
        final JPanel tool = new JPanel();
        tool.setLayout(new BorderLayout());

        final JMenu menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription("Menu of FileMiner application");

        final UpperToolbar toolbar = new UpperToolbar();
        
        tool.add(menu, BorderLayout.NORTH);
        tool.add(toolbar, BorderLayout.SOUTH);
        getContentPane().add(tool, BorderLayout.NORTH);

        // START SPLIT PANEL
        splitPane = new JSplitPane();
        removeSplitKeyBindings();

        // TREE EXPLORER PANE
        final SwingWorker<Void, Void> treeLoader = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                final DefaultTreeModel root = controller.getFileSystemTree();
                treeView = new JScrollPane(new TreeExplorerPanel(root));
                treeView.setPreferredSize(new Dimension(getWidth() / 4, getHeight()));
                splitPane.add(treeView, JSplitPane.LEFT);
                return null;
            }
        };
        treeLoader.addPropertyChangeListener(this);
        treeLoader.execute();
        
        // NODE CONTENT PANEL
        ncp = new NodeContentPanel();
        splitPane.add(ncp, JSplitPane.RIGHT);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        // END SPLIT PANEL

        // INFORMATION PANEL
        infoPanel = new InformationPanel();
        getContentPane().add(infoPanel, BorderLayout.SOUTH);
    }

    private void removeSplitKeyBindings(){
        final ActionMap newSplitActionMap = new ActionMap();
        splitPane.setActionMap(newSplitActionMap);
      }

    private void exitProcedure() {
        setVisible(false);
        dispose();
        controller.quit();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE == evt.getNewValue()) {
            splashScreen.closeSplash();
            ncp.repaint();
            repaint();
            setVisible(true);
        }
    }
}
