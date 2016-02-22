package fileminer.controller;

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
        /*new ControllerImpl();*/
        Controller controller = new ControllerImpl();
        controller.invokesCommand("COPY", "/home/lorenzo/Immagini/aa.jpg", null);
        controller.invokesCommand("PASTE", null, "/home/lorenzo/Video/");
        controller.invokesCommand("MOVE", "/home/lorenzo/Immagini/aa.jpg", "/home/lorenzo/Video/");
    }
}
