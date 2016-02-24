package fileminer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

/**
 * 
 *
 */
public class SplashScreen extends JWindow {

    private static final long serialVersionUID = -227987097234319421L;
    private static final int SIZE_X  = 350;
    private static final int SIZE_Y  = 300;
    
    /**
     * Constructor of FileMiner SplashScreen.
     * @param fileName image path
     * 
     */
    public SplashScreen(final String fileName) {
        super();

        if (Toolkit.getDefaultToolkit().isAlwaysOnTopSupported()) {
            setAlwaysOnTop(true);
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                setLocation((int) getLocation().getX() + e.getX(), (int) getLocation().getY() + e.getY());
            }
        });

        final JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBorder(new LineBorder(Color.darkGray));
        setContentPane(top);

        final JLabel l = new JLabel(new ImageIcon(getClass().getResource(fileName)));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JProgressBar p = new JProgressBar();
        p.setValue(0);
        p.setIndeterminate(true);
        p.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel loading = new JLabel("Loading...");
        loading.setFont(new Font(loading.getFont().getName(), Font.PLAIN, 20));
        loading.setAlignmentX(Component.CENTER_ALIGNMENT);

        getContentPane().add(l);
        getContentPane().add(p);
        getContentPane().add(loading);

        final Dimension windowSize = new Dimension(SIZE_X, SIZE_Y);
        setSize(windowSize);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2 - (windowSize.width/2), screenSize.height/2 - (windowSize.height/2));
    }

    /**
     * Display the splash screen.
     */
    public void openSplash() {
        setVisible(true);

    }

    /**
     * Close the splash screen after GUI initialization.
     */
    public void closeSplash() {
        setVisible(false);
        dispose();
    }
}
