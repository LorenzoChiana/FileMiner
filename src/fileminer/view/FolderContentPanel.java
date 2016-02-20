package fileminer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FolderContentPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 8921940660597369151L;

    public FolderContentPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    

}
