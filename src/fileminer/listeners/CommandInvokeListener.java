package fileminer.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import fileminer.controller.Commands;
import fileminer.controller.Controller;

/**
 * An ActionListener for components that rely on basic operation such new, copy, paste, etc.
 */
public class CommandInvokeListener implements ActionListener {

    private final Controller controller;

    /**
     * Constructor of CommandInvokeListener.
     * @param ctrl the controller that call the operations.
     */
    public CommandInvokeListener(final Controller ctrl) {
        controller = ctrl;
    }
    @Override
    public void actionPerformed(final ActionEvent e) {
        final Object o = e.getSource();
        if (o instanceof JButton || o instanceof JMenuItem) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    for (final Commands cmd : Commands.values()) {
                        if (e.getActionCommand().equals(cmd.toString())) {
                            controller.invokesCommand(cmd, null, null);
                            break;
                        }
                    }
                }
                
            });
        }
    }

}
