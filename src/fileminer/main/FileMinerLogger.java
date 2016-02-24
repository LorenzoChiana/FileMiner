package fileminer.main;

import fileminer.view.FileMinerConsole;

public class FileMinerLogger {

    private static FileMinerLogger singleton;
    private FileMinerConsole console;

    public FileMinerLogger() {
        
    }

    public static FileMinerLogger getInstance() {
        if (singleton == null) {
            singleton = new FileMinerLogger();
        }
        return singleton;
    }

    public void setConsole(final FileMinerConsole cons) {
        console = cons;
    }

    public FileMinerConsole getConsole() {
        return console;
    }
}
