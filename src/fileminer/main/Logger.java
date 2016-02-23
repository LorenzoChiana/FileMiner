package fileminer.main;

public class Logger {

    private static Logger singleton;

    public static Logger getInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
        return singleton;
    }

}
