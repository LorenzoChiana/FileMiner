package fileminer.view.components;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import fileminer.controller.Commands;

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

        /* foreach comando in listaComandi add(...) */
        toolbar.add(createButton("/images/NewFile.png", Commands.NEW.toString(), "Create new item", "New file", al));
        toolbar.addSeparator();
        toolbar.add(createButton("/images/Copy.png", Commands.COPY.toString(), "Copy files or directories", "Copy", al));
        toolbar.add(createButton("/images/Cut.png", Commands.CUT.toString(), "Cut files or directories", "Cut", al));
        toolbar.add(createButton("/images/Paste.png", Commands.PASTE.toString(), "Paste files or directories", "Paste", al));
        toolbar.addSeparator();
        toolbar.add(createButton("/images/Info.png", "INFO", "Display file properties", "Info", al));
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
            //final ImageIcon img = new ImageIcon(imageURL.get());
            //btn.setIcon(new ImageIcon(img.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH), altText));
            btn.setIcon(new ImageIcon(imageURL.get(), altText));
        } else {
            btn.setText(altText);
            System.err.println("Resource not found: " + imagePath);
        }
        btn.setFocusPainted(false);
        return btn;
    }

    public JToolBar getToolBar() {
        return toolbar;
    }
}
