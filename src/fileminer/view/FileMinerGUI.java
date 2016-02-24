package fileminer.view;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultTreeModel;

import fileminer.controller.Controller;
import fileminer.main.FileMinerLogger;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI {

    private static final long serialVersionUID = -3479742762830497941L;
    private static final double SCREENRATIO = 1.5;

    private final JFrame frame;
    private final Controller controller;
    private final SplashScreen splashScreen;

    private JScrollPane treeView;
    private JSplitPane splitPane;
    private JMenuBar menuBar;
    private UpperToolbar toolbar;
    private NodeContentTable ncp;
    private InformationScrollPane info;

    /**
     * Constructor of FileMinerGUI.
     * @param ctrl 
     */
    public FileMinerGUI(final Controller ctrl) {
        controller = ctrl;

        splashScreen = new SplashScreen("/images/Logo256.png");
        splashScreen.setVisible(true);

        frame = new JFrame("FileMiner v1.0");
        initializeFrame();
        createComponents();

    }

    private void initializeFrame() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setLocationByPlatform(true);
        frame.setSize((int) (screenSize.getWidth() / SCREENRATIO), (int) (screenSize.getHeight() / SCREENRATIO));
        frame.setMinimumSize(new Dimension(295, 276));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent event) {
                exitProcedure();
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                final JFrame f = (JFrame) e.getComponent();
                final Dimension newDim = f.getSize();
                FileMinerLogger.getInstance().getConsole().put(this.toString(), newDim.getWidth(), newDim.getHeight());
            }
        });
        frame.setIconImage(new ImageIcon(getClass().getResource("/images/Logo32.png")).getImage());
        frame.getContentPane().setLayout(new BorderLayout());
    }

    private void createComponents() {
        // START TOOL PANEL
        final JPanel tool = new JPanel();
        tool.setLayout(new BorderLayout());

        // MENUBAR
        menuBar = createMenuBar();
        menuBar.getAccessibleContext().setAccessibleDescription("Menu of FileMiner application");
        frame.setJMenuBar(menuBar);
        
        // TOOLBAR
        toolbar = new UpperToolbar();
        frame.getContentPane().add(toolbar.getToolBar(), BorderLayout.NORTH);
        // END TOOL PANEL

        // START SPLIT PANEL
        splitPane = new JSplitPane();
        final ActionMap newSplitActionMap = new ActionMap();
        splitPane.setActionMap(newSplitActionMap);

        // TREE EXPLORER PANE
        final SwingWorker<Void, Void> treeLoader = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                final DefaultTreeModel root = null; //controller.getFileSystemTree();
                treeView = new JScrollPane(new TreeExplorerPanel(root));
                treeView.setPreferredSize(new Dimension(frame.getWidth() / 4, frame.getHeight()));
                splitPane.add(treeView, JSplitPane.LEFT);
                return null;
            }
        };
        treeLoader.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                    splashScreen.closeSplash();
                    frame.repaint();
                    frame.setVisible(true);
                    frame.requestFocus();
                }
            }
        });
        treeLoader.execute();
        
        // NODE CONTENT PANEL
        ncp = new NodeContentTable();
        splitPane.add(ncp.getNodesTable(), JSplitPane.RIGHT);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        // END SPLIT PANEL

        // INFORMATION PANEL
        info = new InformationScrollPane(frame);
        frame.getContentPane().add(info.getScrollPane(), BorderLayout.SOUTH);
        FileMinerLogger.getInstance().setConsole(info.getConsole());
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar2 = new JMenuBar();
        JMenu menu;
        JMenuItem item;
        ImageIcon itemIcon;

        // FILE MENU
        menu = new JMenu("File");
        item = new JMenuItem("New");
        itemIcon = new ImageIcon(getClass().getResource("/images/NewFile.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Copy");
        itemIcon = new ImageIcon(getClass().getResource("/images/Copy.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Cut");
        itemIcon = new ImageIcon(getClass().getResource("/images/Cut.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Paste");
        itemIcon = new ImageIcon(getClass().getResource("/images/Paste.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Exit");
        itemIcon = new ImageIcon(getClass().getResource("/images/Exit.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        item.addActionListener(e -> {
            exitProcedure();
        });
        menu.add(item);
        menuBar2.add(menu);

        // CONFIG MENU
        menu = new JMenu("Config");
        item = new JCheckBoxMenuItem("Set toolbar floatable");
        item.addItemListener(i -> {
            if (i.getStateChange() == ItemEvent.SELECTED) {
                toolbar.getToolBar().setFloatable(true);
                FileMinerLogger.getInstance().getConsole().put(toolbar.toString(), toolbar.getToolBar().isFloatable());
            } else {
                toolbar.getToolBar().setFloatable(false);
                FileMinerLogger.getInstance().getConsole().put(toolbar.toString(), toolbar.getToolBar().isFloatable());
            }
        });
        menu.add(item);
        menuBar2.add(menu);

        return menuBar2;
    }

    private void exitProcedure() {
        frame.setVisible(false);
        frame.dispose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.quit();
            }
        });
        
    }

    @Override
    public String toString() {
        return "Main frame";
    }
}
