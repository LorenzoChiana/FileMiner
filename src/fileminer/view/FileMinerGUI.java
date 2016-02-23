package fileminer.view;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
    private JMenuBar menuBar;
    private UpperToolbar toolbar;
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
        //setResizable(false);
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
        // START TOOL PANEL
        final JPanel tool = new JPanel();
        tool.setLayout(new BorderLayout());

        // MENUBAR
        menuBar = createMenuBar();
        menuBar.getAccessibleContext().setAccessibleDescription("Menu of FileMiner application");
        
        // TOOLBAR
        toolbar = new UpperToolbar();
        
        tool.add(menuBar, BorderLayout.NORTH);
        tool.add(toolbar, BorderLayout.SOUTH);
        getContentPane().add(tool, BorderLayout.NORTH);
        // END TOOL PANEL

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
        infoPanel = new InformationPanel(this);
        getContentPane().add(infoPanel, BorderLayout.SOUTH);
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
                toolbar.setFloatable(true);
            } else {
                toolbar.setFloatable(false);
            }
        });
        menu.add(item);
        menuBar2.add(menu);

        return menuBar2;
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
            repaint();
            setVisible(true);
            requestFocus();
        }
    }
}
