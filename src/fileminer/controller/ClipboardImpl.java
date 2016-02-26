package fileminer.controller;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ClipboardImpl implements Clipboard {
    private List<String> clipboard;
    
    /**
     * ClipboardImpl constructor that initializes the clipboard. 
     */
    public ClipboardImpl() {
        this.clipboard = new ArrayList<>();
    }

    @Override
    public List<String> getPathFiles() {
        return this.clipboard;
    }

    @Override
    public void addPathFiles(final List<String> path) {
        this.clipboard.addAll(path);
    }

    @Override
    public void clean() {
        this.clipboard = new ArrayList<>();
    }

}
