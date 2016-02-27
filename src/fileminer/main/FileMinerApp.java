package fileminer.main;

import javax.swing.UIManager;

import fileminer.controller.Commands;
import fileminer.controller.Controller;
import fileminer.controller.ControllerImpl;

/**
 * 
 * @author Lorenzo Chiana
 *      class that contains the main
 *
 */
public class FileMinerApp {

    /**
     * @param args
     *      param in main
     */
    public static void main(final String... args) {

        if (args.length > 0) {
            useSystemLookAndFeel(Boolean.parseBoolean(args[0]));
        }
        Controller controller = new ControllerImpl();
        //controller.invokesCommand(Commands.COMPRESS);
        //controller.invokesCommand(Commands.LINK);
    }

    private static void useSystemLookAndFeel(final boolean flag) {
        if (flag) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

}