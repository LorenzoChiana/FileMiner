package fileminer.controller;

/**
 * 
 * @author lorenzo
 *      class that implemets OSPropriety
 */
public class OSPropertiesImpl implements OSProperties {
    
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
        return (os.indexOf(OSNames.NUX.toString()) >= 0
                || os.indexOf(OSNames.NIX.toString()) >= 0 
                || os.indexOf(OSNames.AIX.toString()) >= 0);
    }

    @Override
    public boolean isSolaris() {
        return os.indexOf(OSNames.SUNOS.toString()) >= 0;
    }

}
