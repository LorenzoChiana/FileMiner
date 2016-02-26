package fileminer.view.components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fileminer.view.FileMinerConsole;

public class InformationScrollPane {

    private final JScrollPane scrollPane;

    private final FileMinerConsole console;

    public InformationScrollPane(final JFrame owner) {
        console = new FileMinerConsole(10, owner.getWidth() / 12);
        scrollPane = new JScrollPane(console.getTextArea());
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public FileMinerConsole getConsole() {
        return console;
    }
}
