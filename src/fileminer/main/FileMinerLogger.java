package fileminer.main;

import fileminer.view.DefaultConsole;

public class FileMinerLogger {

    private static FileMinerLogger singleton;
    private DefaultConsole console;

    public FileMinerLogger() {
        
    }

    public static synchronized FileMinerLogger getInstance() {
        if (singleton == null) {
            singleton = new FileMinerLogger();
        }
        return singleton;
    }

    public void setConsole(final DefaultConsole cons) {
        console = cons;
    }

    public DefaultConsole getConsole() {
        return console;
    }
}
