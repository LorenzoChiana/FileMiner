package fileminer.controller;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {
    /**
     * This method inizializes the GUI with the root directory.
     */
    void initializesGUI();
    
    /**
     * 
     * @param command
     *          String id of command invoke
     */
    void invokesCommand(String command);
}
