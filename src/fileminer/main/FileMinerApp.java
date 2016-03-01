package fileminer.main;

import java.io.IOException;

import javax.swing.UIManager;

import fileminer.controller.ControllerImpl;
import fileminer.model.test.Test;

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

        // Little Test
        /*Test test = new Test();
        test.modelTest();*/

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