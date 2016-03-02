package fileminer.view;

import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.TreePath;

import fileminer.controller.Controller;
import fileminer.listeners.CommandInvokeListener;
import fileminer.controller.Commands;
import fileminer.main.FileMinerLogger;
import fileminer.model.Bookmark;
import fileminer.view.components.BookmarksDialog;
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
    private final Controller controller;
    private final SplashScreen splashScreen;

    private final CommandInvokeListener cmdListener;

    private UpperToolbar toolbar;
    private TreeExplorer treeExplorer;
    private NodeContentTable ncp;
    private InformationScrollPane info;

    private List<TreePath> selectedNodes;
    private TreePath currentDir;

    /**
     * Constructor of FileMinerGUI.
     * @param ctrl the view observer
     */
    public FileMinerGUI(final Controller ctrl) {
        controller = ctrl;

        frame = new JFrame("FileMiner");
        cmdListener = new CommandInvokeListener(ctrl);
        splashScreen = new SplashScreen(ResourcePath.LOGO_256);
        splashScreen.setVisible(true);

        new SwingWorker<Void, Void>() {

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
                controller.getOSInfo();
                frame.requestFocus();
            }
        }.execute();
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
        JScrollPane treeView,
                    ncpView;
        JSplitPane splitPane;

        // START TOOL PANEL
        final JPanel tool = new JPanel();
        tool.setLayout(new BorderLayout());

        // MENUBAR
        menuBar = createMenuBar();
        menuBar.getAccessibleContext().setAccessibleDescription("Menu of FileMiner application");
        frame.setJMenuBar(menuBar);

        // TOOLBAR
        toolbar = new UpperToolbar(cmdListener);
        frame.getContentPane().add(toolbar.getToolBar(), BorderLayout.NORTH);
        // END TOOL PANEL

        // START SPLIT PANEL
        splitPane = new JSplitPane();

        treeExplorer = new TreeExplorer(controller.getFileSystem(), this);
        treeView = new JScrollPane(treeExplorer.getTree());
        treeView.setPreferredSize(new Dimension(frame.getWidth() / 4, frame.getHeight()));
        splitPane.add(treeView, JSplitPane.LEFT);

        // NODE CONTENT PANEL
        selectedNodes = new ArrayList<>();
        ncp = new NodeContentTable(controller.getFileSystem(), this);
        ncpView = new JScrollPane(ncp.getTable());
        splitPane.add(ncpView, JSplitPane.RIGHT);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        // END SPLIT PANEL

        // INFORMATION PANEL
        info = new InformationScrollPane(frame);
        frame.getContentPane().add(info.getScrollPane(), BorderLayout.SOUTH);
        FileMinerLogger.getInstance().setConsole(info.getConsole());
    }

    /**
     * Get user input name.
     * @param option 0 is file, 1 is directory, 2 is link
     * @return string
     */
    public String getDialogString(final int option) {
        final String name = (String) JOptionPane.showInputDialog(frame,
                "Enter text:",
                option == 0 ? "New file" : (option == 1 ? "New dir" : "New link"),
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        if (name == null || name.length() <= 0) {
            return null;
        }

        for (final char c : name.toCharArray()) {
            switch (c) {
            case '/':
            case '\\':
            case ':':
            case '"':
            case '*':
            case '?':
            case '<':
            case '>':
            case '|':
                JOptionPane.showMessageDialog(frame, "Incorrect name!", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            default: break;
            }
        }

        return name;
    }

    /**
     * Get user answer.
     * @return 0 if yes, 1 if no
     */
    public boolean getConfirmDialog() {
        final int n = JOptionPane.showConfirmDialog(frame,
                                                    "Are you sure?",
                                                    "Think about it",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.WARNING_MESSAGE);
        if (n == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        JMenu menu, menuNew;
        JMenuItem item;
        ImageIcon itemIcon, menuIcon;

        // FILE MENU
        menu = new JMenu("File");

        menuNew = new JMenu("New");
        menuIcon = new ImageIcon(getClass().getResource(ResourcePath.NEW_ICON));
        menuNew.setIcon(new ImageIcon(menuIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        item = new JMenuItem("File");
        item.setActionCommand(Commands.NEW_FILE.toString());
        item.addActionListener(cmdListener);
        menuNew.add(item);
        item = new JMenuItem("Directory");
        item.setActionCommand(Commands.NEW_DIR.toString());
        item.addActionListener(cmdListener);
        menuNew.add(item);
        item = new JMenuItem("Link");
        item.setActionCommand(Commands.NEW_LINK.toString());
        item.addActionListener(cmdListener);
        menuNew.add(item);
        item = new JMenuItem("Zip");
        item.setActionCommand(Commands.ZIP.toString());
        item.addActionListener(cmdListener);
        menuNew.add(item);
        menu.add(menuNew);
        item = new JMenuItem("Open");
        item.setActionCommand(Commands.OPEN.toString());
        item.addActionListener(cmdListener);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.OPEN_ICON));
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
        menuBar.add(menu);

        // EDIT MENU
        menu = new JMenu("Edit");
        item = new JMenuItem("Copy");
        item.setActionCommand(Commands.COPY.toString());
        item.addActionListener(cmdListener);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.COPY_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Cut");
        item.setActionCommand(Commands.CUT.toString());
        item.addActionListener(cmdListener);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.CUT_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Paste");
        item.setActionCommand(Commands.PASTE.toString());
        item.addActionListener(cmdListener);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.PASTE_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        item = new JMenuItem("Delete");
        item.setActionCommand(Commands.DELETE.toString());
        item.addActionListener(cmdListener);
        itemIcon = new ImageIcon(getClass().getResource(ResourcePath.DELETE_ICON));
        item.setIcon(new ImageIcon(itemIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Unzip");
        item.setActionCommand(Commands.UNZIP.toString());
        item.addActionListener(cmdListener);
        menu.add(item);
        menuBar.add(menu);
        
        // BOOKMARKS
        menu = new JMenu("Bookmarks");
        item = new JMenuItem("Add bookmark");
        item.setActionCommand(Commands.NEW_BOOKMARK.toString());
        item.addActionListener(cmdListener);
        menu.add(item);
        item = new JMenuItem("Open bookmark");
        item.setActionCommand(Commands.OPEN_BOOKMARK.toString());
        item.addActionListener(cmdListener);
        menu.add(item);
        item = new JMenuItem("Delete bookmark");
        item.setActionCommand(Commands.DELETE_BOOKMARK.toString());
        item.addActionListener(cmdListener);
        menu.add(item);
        menuBar.add(menu);
        
        // CONFIG MENU
        menu = new JMenu("Config");
        item = new JMenuItem("Clear console");
        item.addActionListener(e -> {
            FileMinerLogger.getInstance().getConsole().clear();
        });
        menu.add(item);
        menuBar.add(menu);

        // HELP MENU
        menu = new JMenu("Help");
        item = new JMenuItem("About OS");
        item.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                                          controller.getOSInfo(),
                                          "OS info",
                                          JOptionPane.INFORMATION_MESSAGE);
        });
        menu.add(item);
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
        menuBar.add(menu);

        return menuBar;
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
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public CommandInvokeListener getCommandListener() {
        return cmdListener;
    }

    /**
     * Open the bookmarks dialog. The method returns only when the user close the dialog.
     * @param bookmarks Bookmark object
     * @param viewType true to open a bookmark, false to choose what bookmark should be deleted
     */
    public void openBookmarksDialog(final Bookmark bookmarks, final boolean viewType) {
        new BookmarksDialog(this, bookmarks, viewType);
    }

    @Override
    public void addPathToChronology(final TreePath path) {
        controller.getChronology().addDirectory(currentDir);
    }

    @Override
    public List<TreePath> getSelectedItems() {
        return selectedNodes;
    }

    @Override
    public void clearSelectedItems() {
        selectedNodes = new ArrayList<TreePath>();
    }

    @Override
    public void setCurrentDir(final TreePath path) {
        currentDir = path;
        toolbar.setToolbarDir(path);
    }

    @Override
    public TreePath getCurrentDir() {
        return currentDir;
    }

    @Override
    public void updateNodesTable(final TreePath path) {
        ncp.generateTableByPath(this, controller.getFileSystem(), path);
    }
}
