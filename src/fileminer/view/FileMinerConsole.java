package fileminer.view;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * A customized class that encapsulate a JTextArea component to print logs of application.
 * @author Michele
 *
 */
public final class FileMinerConsole implements DefaultConsole {

    private final JTextArea console;

    /**
     * Constructor of FileMinerConsole.
     * @param lines 
     * @param rows 
     */
    public FileMinerConsole(final int lines, final int rows) {
        console = new JTextArea(lines, rows);
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 13));
    }

    /**
     * @return the component
     */
    public JTextArea getTextArea() {
        return console;
    }

    @Override
    public void put(final String invoker, final Object... args) {
        console.append(invoker + " -> ");
        
        for (final Object obj : args) {
            switch (obj.getClass().getName()) {
            case "java.lang.String": console.append(obj + ","); break;
            case "Z": console.append((boolean) obj + ","); break;
            case "B": console.append((byte) obj + ","); break;
            case "C": console.append((char) obj + ","); break;
            case "D": console.append((double) obj + ","); break;
            case "F": console.append((float) obj + ","); break;
            case "I": console.append((int) obj + ","); break;
            case "J": console.append((long) obj + ","); break;
            //case "S": console.append((short) obj + ","); break;
            default: console.append(obj.toString() + ",");
            }
            
        }
        console.append("\n");
        console.setCaretPosition(console.getDocument().getLength());
    }

    @Override
    public void clear() {
        console.setText("");
    }

    @Override
    public void putString(final String arg) {
        console.append(arg + "\n");
        console.setCaretPosition(console.getDocument().getLength());
    }

    @Override
    public void putStringLater(String arg) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                console.append(arg + "\n");
                console.setCaretPosition(console.getDocument().getLength());
            }
        });
    }
}
