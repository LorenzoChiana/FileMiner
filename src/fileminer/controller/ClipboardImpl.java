package fileminer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreePath;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ClipboardImpl implements Clipboard {

    private List<TreePath> clipboard;
    private boolean isCopy;
    
    /**
     * ClipboardImpl constructor that initializes the clipboard. 
     */
    public ClipboardImpl() {
        this.clipboard = new ArrayList<>();
    }

    @Override
    public List<TreePath> getPathFiles() {
        return this.clipboard;
    }

    @Override
    public void addPathFiles(final List<TreePath> path) {
        this.clipboard = new ArrayList<>();
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
