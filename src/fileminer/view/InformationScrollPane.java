package fileminer.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InformationScrollPane {

    private JScrollPane scrollPane;

    private JTextArea textArea;

    public InformationScrollPane(final JFrame owner) {
        textArea = new JTextArea(10, owner.getWidth());
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
