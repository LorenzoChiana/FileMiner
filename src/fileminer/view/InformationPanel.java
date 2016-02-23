package fileminer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class InformationPanel extends JPanel {

    private static final long serialVersionUID = -824801462236575279L;
    private static final int INITIAL_HEIGHT = 150;
    private final JTextArea textArea;

    public InformationPanel(final JFrame owner) {
        setPreferredSize(new Dimension(owner.getWidth(), INITIAL_HEIGHT));
        textArea = new JTextArea(0, 3);
        textArea.setEditable(false);
        textArea.setPreferredSize(getPreferredSize());
        textArea.setBorder(new LineBorder(Color.darkGray));
        add(textArea, BorderLayout.CENTER);
    }

}
