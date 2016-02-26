package fileminer.controller;

/**
 * 
 * @author lorenzo
 *      class that implemets OSPropriety
 */
public final class OSPropertiesImpl implements OSProperties {
    
    private final String os = System.getProperty("os.name").toUpperCase();
    
    @Override
    public boolean isWindows() {
        return os.indexOf(OSNames.WIN.toString()) >= 0;
    }

    @Override
    public boolean isMac() {
        return os.indexOf(OSNames.MAC.toString()) >= 0;
    }

    @Override
    public boolean isUnix() {
        return os.indexOf(OSNames.NUX.toString()) >= 0
                || os.indexOf(OSNames.NIX.toString()) >= 0 
                || os.indexOf(OSNames.AIX.toString()) >= 0;
    }

    @Override
    public boolean isSolaris() {
        return os.indexOf(OSNames.SUNOS.toString()) >= 0;
    }

    @Override
    public String toString() {
        return   "==========> STARTUP INFO <==========\n"
               + "Java: " + System.getProperty("java.vendor") + " (" + System.getProperty("java.version") + ")" + "\n"
               + "OS name: " + os + "\n"
               + "OS architecture: " + System.getProperty("os.arch") + "\n"
               + "OS version: " + System.getProperty("os.version") + "\n"
               + "Logged user: " + System.getProperty("user.name") + "\n"
               + "User home: " + System.getProperty("user.home") + "\n"
               + "====================================\n";
    }
}
