package fileminer.view;

/**
 * Default console methods for this application.
 *
 */
public interface DefaultConsole {

    /**
     * Print string on console.
     * @param arg string argument
     */
    void putString(String arg);

    /**
     * Print string on console using EventQueue's invokeLater method.
     * @param arg string argument
     */
    void putStringLater(String arg);

    /**
     * Print info on console with invoker name.
     * @param invoker who want to print info
     * @param args object arguments
     */
    void put(String invoker, Object... args);

    /**
     * Clear the console.
     */
    void clear();
}
