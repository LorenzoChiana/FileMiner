package fileminer.view.components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fileminer.view.FileMinerConsole;

/**
 * This class manage the console scroll pane where logs are displayed. 
 * @author Michele
 *
 */
public class InformationScrollPane {

    private final JScrollPane scrollPane;

    private final FileMinerConsole console;

    /**
     * Constructor of InformationScrollPane.
     * @param owner the main frame
     */
    public InformationScrollPane(final JFrame owner) {
        console = new FileMinerConsole(10, owner.getWidth() / 12);
        scrollPane = new JScrollPane(console.getTextArea());
    }

    /**
     * Get the scroll pane.
     * @return JScrollPane object
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Get the console.
     * @return FileMinerConsole object
     */
    public FileMinerConsole getConsole() {
        return console;
    }
}
