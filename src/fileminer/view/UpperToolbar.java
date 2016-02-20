package fileminer.view;

import java.awt.FlowLayout;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author Michele Durante
 *
 */
public class UpperToolbar extends JToolBar {

    private static final long serialVersionUID = 712167899479629945L;

    private static final String IMAGESPATH = "/images/"; /* da reperire dal model */

    // da reperire dal model
    private static final String NEWFILE = "newfile";
    private static final String COPY = "copy";
    private static final String CUT = "cut";
    private static final String PASTE = "paste";
    private static final String INFO = "info";

    public UpperToolbar(/* listaComandi */) {
        super();
        this.setFloatable(false);
        this.setRollover(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        /* foreach comando in listaComandi add(...) */
        this.add(createButton(IMAGESPATH + "NewFile.png", NEWFILE, "Create new file", "New file"));
        this.addSeparator();
        this.add(createButton(IMAGESPATH + "Copy.png", COPY, "Copy files or directories", "Copy"));
        this.add(createButton(IMAGESPATH + "Cut.png", CUT, "Cut files or directories", "Cut"));
        this.add(createButton(IMAGESPATH + "Paste.png", PASTE, "Paste files or directories", "Paste"));
        this.addSeparator();
        this.add(createButton(IMAGESPATH + "Info.png", INFO, "Display info about application", "Info"));
    }

    /**
     * @param imagePath image path
     * @param actionCmd action command
     * @param toolTipText roll-over description
     * @param altText button name if image not found
     * @return button with imageicon
     */
    protected JButton createButton(final String imagePath, final String actionCmd, final String toolTipText, final String altText) {
        final JButton btn = new JButton();
        final Optional<URL> imageURL = Optional.ofNullable(this.getClass().getResource(imagePath));
        btn.setActionCommand(actionCmd);
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
}
