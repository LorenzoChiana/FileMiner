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
		controller.invokesCommand("COPY", "C:\\Users\\Daniele\\Desktop\\prova.txt", null,false);
		controller.invokesCommand("PASTE", null, "C:\\Users\\Daniele\\Desktop\\TORRENT",false);
		//controller.invokesCommand("MOVE", "/home/lorenzo/Immagini/aa.jpg", "/home/lorenzo/Video/");
	}
}
