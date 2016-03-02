package fileminer.main;

import fileminer.view.DefaultConsole;

/**
 * A FileMinerLogger object is used to log messages.
 * @author Michele
 *
 */
public class FileMinerLogger {

    private static FileMinerLogger singleton;
    private DefaultConsole console;

    /**
     * Get the runtime object of FileMinerLogger.
     * @return the FileMinerLogger object
     */
    public static synchronized FileMinerLogger getInstance() {
        if (singleton == null) {
            singleton = new FileMinerLogger();
        }
        return singleton;
    }

    /**
     * Set a console to this object to define where the logs should be printed.
     * @param cons any class that implements DefaultConsole
     */
    public void setConsole(final DefaultConsole cons) {
        console = cons;
    }

    /**
     * Get the current setted console.
     * @return the console
     */
    public DefaultConsole getConsole() {
        return console;
    }
}
