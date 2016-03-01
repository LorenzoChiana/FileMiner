package fileminer.main;

import javax.swing.UIManager;

import fileminer.controller.ControllerImpl;

/**
 * Class that contains the main.
 * @author Lorenzo Chiana
 */
public final class FileMinerApp {

    /**
     * @param args
     *      param in main
     */
    public static void main(final String... args) {

        if (args.length > 0) {
            useSystemLookAndFeel(Boolean.parseBoolean(args[0]));
        }
        new ControllerImpl();
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