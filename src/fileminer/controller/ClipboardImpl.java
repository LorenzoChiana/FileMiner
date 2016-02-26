package fileminer.controller;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ClipboardImpl implements Clipboard {
    private List<String> clipboard = new ArrayList<>();

    @Override
    public List<String> getPathFiles() {
        return this.clipboard;
    }

    @Override
    public void addPathFiles(final String path) {
        this.clipboard.add(path);
    }

    @Override
    public void clean() {
        this.clipboard = new ArrayList<>();
    }

}
