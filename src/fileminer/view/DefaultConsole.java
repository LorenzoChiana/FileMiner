package fileminer.view;

/**
 * Default console methods for this application.
 *
 */
public interface DefaultConsole {

    /**
     * Print info.
     * @param invoker who want to print info
     * @param objects log arguments
     */
    void put(String invoker, Object... objects);

    /**
     * Clear the console.
     */
    void clear();
}
