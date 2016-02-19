package fileminer.Controller;

/**
 * 
 * @author Lorenzo Chiana
 *      class that contains the main
 *
 */
public class FileMinerApp { 
    /**
     * 
     * @param args
     *      param in main
     */
    public static void main(final String[] args) {

        final Controller controller = new ControllerImpl();
        
        controller.inizializeGUI();
    }
}
