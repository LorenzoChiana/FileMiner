package fileminer.view.components;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import fileminer.controller.Commands;
import fileminer.view.ResourcePath;

/**
 * @author Michele Durante
 *
 */
public class UpperToolbar {

    private final JToolBar toolbar;

    public UpperToolbar(final ActionListener al) {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        toolbar.add(createButton(ResourcePath.LEFT_ARROW_ICON, Commands.BACK.toString(), "Previous visited dir", "Back", al));
        toolbar.add(createButton(ResourcePath.RIGHT_ARROW_ICON, Commands.NEXT.toString(), "Next visited dir", "Next", al));
        toolbar.addSeparator();
        toolbar.add(createButton(ResourcePath.NEW_ICON, Commands.NEW.toString(), "Create new item", "New item", al));
        toolbar.addSeparator();
        toolbar.add(createButton(ResourcePath.COPY_ICON, Commands.COPY.toString(), "Copy files or directories", "Copy", al));
        toolbar.add(createButton(ResourcePath.CUT_ICON, Commands.CUT.toString(), "Cut files or directories", "Cut", al));
        toolbar.add(createButton(ResourcePath.PASTE_ICON, Commands.PASTE.toString(), "Paste files or directories", "Paste", al));
        toolbar.add(createButton(ResourcePath.DELETE_ICON, Commands.DELETE.toString(), "Delete files or directories", "Delete", al));
        toolbar.addSeparator();
        toolbar.add(createButton(ResourcePath.OPEN_ICON, Commands.OPEN.toString(), "Open file in OS", "Open", al));
    }

    /**
     * @param imagePath image path
     * @param actionCmd action command
     * @param toolTipText roll-over description
     * @param altText button name if image not found
     * @return button with imageicon
     */
    private JButton createButton(final String imagePath, final String actionCmd, final String toolTipText, final String altText, final ActionListener al) {
        final JButton btn = new JButton();
        final Optional<URL> imageURL = Optional.ofNullable(this.getClass().getResource(imagePath));
        btn.setActionCommand(actionCmd);
        btn.addActionListener(al);
        btn.setToolTipText(toolTipText);
        if (imageURL.isPresent()) {
            btn.setIcon(new ImageIcon(imageURL.get(), altText));
        } else {
            btn.setText(altText);
            System.err.println("Resource not found: " + imagePath);
        }
        btn.setFocusPainted(false);
        return btn;
    }

    /**
     * Get the toolbar component.
     * @return toolbar
     */
    public JToolBar getToolBar() {
        return toolbar;
    }
 
    @Override
    public String toString() {
        return "UpperToolbar";
    }
}
