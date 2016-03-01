package fileminer.view.components;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fileminer.controller.Commands;
import fileminer.model.Node;
import fileminer.view.ResourcePath;

/**
 * @author Michele Durante
 *
 */
public class UpperToolbar {

    private final JToolBar toolbar;
    private final JLabel currentToolbarDir;

    public UpperToolbar(final ActionListener listener) {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        toolbar.add(createButton(ResourcePath.LEFT_ARROW_ICON, Commands.BACK.toString(), "Previous visited dir", "Back", listener));
        toolbar.add(createButton(ResourcePath.RIGHT_ARROW_ICON, Commands.NEXT.toString(), "Next visited dir", "Next", listener));
        toolbar.add(createButton(ResourcePath.REFRESH_ICON, Commands.REFRESH.toString(), "Refresh current dir", "Refresh", listener));
        toolbar.addSeparator();
        currentToolbarDir = new JLabel();
        currentToolbarDir.setFont(new Font(currentToolbarDir.getFont().getName(), Font.PLAIN, 16));
        currentToolbarDir.setMaximumSize(currentToolbarDir.getPreferredSize());
        toolbar.add(currentToolbarDir);
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

    public void setToolbarDir(final TreePath dirPath) {
    	final DefaultMutableTreeNode dirNode = (DefaultMutableTreeNode) dirPath.getLastPathComponent();
    	final Node node = (Node) dirNode.getUserObject();
    	currentToolbarDir.setText(node.getFile().getAbsolutePath());
    }

    @Override
    public String toString() {
        return "UpperToolbar";
    }
}
