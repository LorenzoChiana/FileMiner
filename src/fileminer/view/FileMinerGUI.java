package fileminer.view;

import java.awt.*;
import java.awt.event.ItemEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

import fileminer.controller.Controller;
import fileminer.listeners.CommandInvokeListener;
import fileminer.controller.Commands;
import fileminer.main.FileMinerLogger;
import fileminer.view.ResourcePath;
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
    private final FileMinerLogger logger;
    private final Controller controller;
    private final SplashScreen splashScreen;

    private UpperToolbar toolbar;
    private TreeExplorer treeExplorer;
    private NodeContentTable ncp;
    private InformationScrollPane info;

    /**
     * Constructor of FileMinerGUI.
     * @param ctrl the view observer
     */
    public FileMinerGUI(final Controller ctrl) {
        controller = ctrl;
        logger = FileMinerLogger.getInstance();

        frame = new JFrame("FileMiner");
        splashScreen = new SplashScreen(ResourcePath.LOGO_256);
        splashScreen.setVisible(true);

        final SwingWorker<Void, Void> guiBuilder = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                initializeFrame();
                createComponents();
                return null;
            }
            @Override
            protected void done() {
                splashScreen.closeSplash();
                frame.setVisible(true);
                controller.printOSInfo();
                frame.requestFocus();
            }
        };
        guiBuilder.execute();
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

        frame.setIconImage(new ImageIcon(getClass().getResource(ResourcePath.LOGO_32)).getImage());
        frame.getContentPane().setLayout(new BorderLayout());
    }

    private void createComponents() {

        JMenuBar menuBar;
        JScrollPane treeView, ncpView;
        JSplitPane splitPane;

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

        treeExplorer = new TreeExplorer(controller.getFileSystem(), this);
        treeView = new JScrollPane(treeExplorer.getTree());
        treeView.setPreferredSize(new Dimension(frame.getWidth() / 4, frame.getHeight()));
        splitPane.add(treeView, JSplitPane.LEFT);

        // NODE CONTENT PANEL
        ncp = new NodeContentTable();
        ncpView = new JScrollPane(ncp.getNodesTable());
        splitPane.add(ncpView, JSplitPane.RIGHT);
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
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.NEW_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Exit");
        item.setActionCommand("EXIT");
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.EXIT_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        item.addActionListener(e -> {
            exitProcedure();
        });
        menu.add(item);
        menuBar2.add(menu);

        // EDIT MENU
        menu = new JMenu("Edit");
        item = new JMenuItem("Copy");
        item.setActionCommand(Commands.COPY.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.COPY_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Cut");
        item.setActionCommand(Commands.CUT.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.CUT_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Paste");
        item.setActionCommand(Commands.PASTE.toString());
        item.addActionListener(cil);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.PASTE_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
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
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.INFO_ICON));
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

	@Override
	public List<String> getSelectedItems() {
		return null;
	}

	@Override
	public String getCurrentDir() {
		return null;
	}
}
