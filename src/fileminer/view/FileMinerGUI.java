package fileminer.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import fileminer.controller.Controller;
import fileminer.listeners.CommandInvokeListener;
import fileminer.listeners.TreeExpandListener;
import fileminer.listeners.TreeNodeSelectionListener;
import fileminer.controller.Commands;
import fileminer.main.FileMinerLogger;
import fileminer.view.cellrenderer.NodeTreeCellRenderer;
import fileminer.view.components.InformationScrollPane;
import fileminer.view.components.NodeContentTable;
import fileminer.view.components.TreeExplorer;
import fileminer.view.components.UpperToolbar;

/**
 * @author Michele Durante
 *
 */
public class FileMinerGUI implements DefaultGUI {

    private static final double SCREENRATIO = 1.5;

    private final JFrame frame;
    private final FileMinerGUI self;
    private final FileMinerLogger logger;
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
     * @param ctrl the view observer
     */
    public FileMinerGUI(final Controller ctrl) {
        controller = ctrl;
        self = this;
        logger = FileMinerLogger.getInstance();

        splashScreen = new SplashScreen("/images/Logo256.png");
        splashScreen.setVisible(true);

        frame = new JFrame("FileMiner");
        initializeFrame();
        createComponents();
    }

    private void initializeFrame() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setLocationByPlatform(true);
        frame.setSize((int) (screenSize.getWidth() / SCREENRATIO), (int) (screenSize.getHeight() / SCREENRATIO));
        frame.setMinimumSize(new Dimension((int) screenSize.getWidth() / 8, (int) screenSize.getHeight() / 8));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent event) {
                exitProcedure();
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
        toolbar = new UpperToolbar(new CommandInvokeListener(controller));
        frame.getContentPane().add(toolbar.getToolBar(), BorderLayout.NORTH);
        // END TOOL PANEL

        // START SPLIT PANEL
        splitPane = new JSplitPane();
        final ActionMap newSplitActionMap = new ActionMap();
        splitPane.setActionMap(newSplitActionMap);
        

        // TREE EXPLORER PANE
        final SwingWorker<Void, Void> treeLoader = new SwingWorker<Void, Void>() {
            private DefaultTreeModel treeModel;
            private TreeExplorer treeExplorer;
            @Override
            protected Void doInBackground() throws Exception {
                treeModel = controller.getFileSystem().getTree();
                return null;
            }
            @Override
            protected void done() {
                treeExplorer = new TreeExplorer(treeModel);
                treeExplorer.getTree().addTreeSelectionListener(new TreeNodeSelectionListener(controller.getFileSystem(), self));
                treeExplorer.getTree().addTreeWillExpandListener(new TreeExpandListener(controller.getFileSystem()));
                treeExplorer.getTree().setCellRenderer(new NodeTreeCellRenderer(controller.getFileSystem()));
                treeView = new JScrollPane(treeExplorer.getTree());
                treeView.setPreferredSize(new Dimension(frame.getWidth() / 4, frame.getHeight()));
                splitPane.add(treeView, JSplitPane.LEFT);
                frame.repaint();
            }
        };
        treeLoader.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName()) && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                    splashScreen.closeSplash();
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
        logger.setConsole(info.getConsole());
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar2 = new JMenuBar();
        JMenu menu;
        JMenuItem item;
        ImageIcon itemIcon;
        final CommandInvokeListener cil = new CommandInvokeListener(controller);

        // FILE MENU
        menu = new JMenu("File");
        item = new JMenuItem("New");
        item.setActionCommand(Commands.NEW.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource("/images/NewFile.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Copy");
        item.setActionCommand(Commands.COPY.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource("/images/Copy.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Cut");
        item.setActionCommand(Commands.CUT.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource("/images/Cut.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Paste");
        item.setActionCommand(Commands.PASTE.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource("/images/Paste.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Exit");
        item.setActionCommand("EXIT");
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
                logger.getConsole().put(toolbar.toString(), toolbar.getToolBar().isFloatable());
            } else {
                toolbar.getToolBar().setFloatable(false);
                logger.getConsole().put(toolbar.toString(), toolbar.getToolBar().isFloatable());
            }
        });
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Clear console");
        item.addActionListener(e -> {
            logger.getConsole().clear();
        });
        menu.add(item);
        menuBar2.add(menu);

        // HELP MENU
        menu = new JMenu("?");
        item = new JMenuItem("About FileMiner");
        itemIcon = new ImageIcon(getClass().getResource("/images/Info.png"));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                                          "Created by Chiana Lorenzo, Durante Michele, Gambaletta Daniele",
                                          "About FileMiner",
                                          JOptionPane.INFORMATION_MESSAGE,
                                          null);
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
}
