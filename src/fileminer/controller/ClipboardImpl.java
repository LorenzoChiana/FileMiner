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
    private boolean isCopy;
    
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

    @Override
    public boolean isEmpty() {
        return this.clipboard.isEmpty();
    }

    @Override
    public void setParameter(final boolean isCopy) {
        this.isCopy = isCopy;
    }

    @Override
    public boolean getParameter() {
        return this.isCopy;
    }

}
