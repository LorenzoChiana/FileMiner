package fileminer.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ChronologyImpl implements Chronology {
    private List<String> chronology = new ArrayList<>();
    private Integer indexCurrentDir;
    
    @Override
    public void addDirectory(final String path) {
        this.chronology.add(path);
        this.indexCurrentDir = this.chronology.size();
    }

    @Override
    public List<String> getChronology() {
        return this.chronology;
    }

    @Override
    public void nextDir() {
        if (this.indexCurrentDir < this.chronology.size()) {
            this.indexCurrentDir++;            
        }
    }

    @Override
    public void prevDir() {
        if (this.indexCurrentDir == 0) {
            this.indexCurrentDir--;
        }
    }

    @Override
    public String getCurrentDirectory() {
        return this.chronology.get(this.indexCurrentDir);
    }

}
