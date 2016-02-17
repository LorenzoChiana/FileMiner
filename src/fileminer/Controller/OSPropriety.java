package fileminer.Controller;

/**
 * 
 * @author lorenzo
 *      interface used to know Operating System propriety
 *
 */
public interface OSPropriety {
    
    /**
     * 
     * @return true if the OS is Windwos
     */
    boolean isWindows();
    
    /**
     * 
     * @return true if the OS is Mac
     */
    boolean isMac();
    
    /**
     * 
     * @return true if the OS is Unix
     */
    boolean isUnix();
    
    /**
     * 
     * @return true if the OS is Solaris
     */
    boolean isSolaris();

    /**
     * 
     * @author lorenzo
     *          enum with Operating System's name
     */
    enum OSNames {
        WIN, 
        MAC, 
        NIX, 
        NUX, 
        AIX, 
        SUNOS;
    }
}
