package fileminer.view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.tree.TreePath;

import fileminer.model.Bookmark;
import fileminer.view.FileMinerGUI;

public class BookmarksDialog implements ActionListener {

    private final JDialog dialog;
    private final FileMinerGUI view;
    private final Bookmark bookmarks;
    private final boolean viewType;

    private JList<TreePath> bookmarksList;

    public BookmarksDialog(final FileMinerGUI v, final Bookmark b, final boolean viewType) {
    	this.view = v;
    	this.bookmarks = b;
    	this.viewType = viewType;

        this.dialog = new JDialog(view.getFrame(), this.viewType ? "Open bookmark" : "Delete bookmark", true);
        this.dialog.setContentPane(this.viewType ? createViewPane() : createDeletePane());
        this.dialog.setSize(new Dimension(150, 300));
        this.dialog.setVisible(true);
    }

    private JLabel createViewPane() {
        final JLabel pane = new JLabel();
        pane.setLayout(new BorderLayout());

        final JLabel label = new JLabel("Choose a bookmark to open:");
        pane.add(label, BorderLayout.NORTH);

        bookmarksList = new JList<TreePath>((TreePath[]) bookmarks.getBookmarks().toArray());
        bookmarksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookmarksList.setSelectedIndex(0);
        pane.add(bookmarksList, BorderLayout.CENTER);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        final JButton ok = new JButton("OK");
        ok.setActionCommand("OK");
        ok.addActionListener(this);

        final JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("CANCEL");
        cancel.addActionListener(this);

        pane.add(buttons, BorderLayout.SOUTH);

        return pane;
    }

    private JLabel createDeletePane() {
    	final JLabel pane = new JLabel();
        pane.setLayout(new BorderLayout());

        final JLabel label = new JLabel("Choose a bookmark to delete:");
        pane.add(label, BorderLayout.NORTH);

        bookmarksList = new JList<TreePath>((TreePath[]) bookmarks.getBookmarks().toArray());
        bookmarksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookmarksList.setSelectedIndex(0);
        pane.add(bookmarksList, BorderLayout.CENTER);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        final JButton ok = new JButton("OK");
        ok.setActionCommand("OK");
        ok.addActionListener(this);

        final JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("CANCEL");
        cancel.addActionListener(this);

        pane.add(buttons, BorderLayout.SOUTH);

        return pane;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
    	final int index = bookmarksList.getSelectedIndex();

    	if (viewType && index != -1) {
    		if (e.getActionCommand().equals("OK")) {
    			view.getSelectedItems().add((TreePath) bookmarksList.getModel().getElementAt(index));
    		}
    	} else if (!viewType && index != -1) {
    		if (e.getActionCommand().equals("OK")) {
    			view.getSelectedItems().add((TreePath) bookmarksList.getModel().getElementAt(index));
    		}
    	}

    	dialog.setVisible(false);
    	dialog.dispose();
    }
}
