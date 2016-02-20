package fileminer.model;

import java.io.File;

// Classe per la creazione di un nodo
/**
 * @author Daniele
 *
 */
public class NodeImpl implements Node {
    
    private File file;
    
    /**
     * @param file file del nodo
     */
    public NodeImpl(final File file) {
        this.file = file;
    }
    
    /**
     * @return file
     */
    public File getFile() {
        return this.file;
    }
}
