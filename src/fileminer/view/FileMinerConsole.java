package fileminer.view;

import java.awt.Font;
import javax.swing.JTextArea;

public class FileMinerConsole {

    private final JTextArea console;

    public FileMinerConsole(final int lines, final int rows) {
        console = new JTextArea(lines, rows);
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 13));
    }

    public JTextArea getTextArea() {
        return console;
    }

    public void put(final String invoker, final Object... objects) {
        console.append(invoker + " -> ");
        
        for (final Object obj : objects) {
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
    }
}
