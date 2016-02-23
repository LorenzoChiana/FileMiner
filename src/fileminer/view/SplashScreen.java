package fileminer.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * 
 *
 */
public class SplashScreen extends JWindow implements MouseMotionListener {

    private static final long serialVersionUID = -227987097234319421L;
    
    /**
     * Constructor of FileMiner SplashScreen.
     * @param fileName image path
     * 
     */
    public SplashScreen(final String fileName) {
        super();
        setAlwaysOnTop(true);
        addMouseMotionListener(this);
        final JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBorder(new LineBorder(Color.black));
        setContentPane(top);

        final JLabel l = new JLabel(new ImageIcon(getClass().getResource(fileName)));
        final JProgressBar p = new JProgressBar();
        p.setValue(0);
        p.setIndeterminate(true);
        
        getContentPane().add(l, BorderLayout.CENTER);
        getContentPane().add(p, BorderLayout.SOUTH);
        pack();

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension labelSize = top.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
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

    @Override
    public void mouseDragged(MouseEvent e) {
        /*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLocation(e.getX(), e.getY());
            }
            
        });
        */
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
