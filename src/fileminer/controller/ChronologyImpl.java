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
    private Integer indexCurrentDir = this.chronology.size() - 1;
    
    @Override
    public void addDirectory(final String path) {
        if (this.indexCurrentDir == this.chronology.size() - 1) {
            this.chronology.add(path);            
        } else {
            this.chronology.set(this.indexCurrentDir + 1, path);
            for (int i = this.indexCurrentDir + 2; i < this.chronology.size(); i++) {
                this.chronology.remove(i);
            }
        }
        this.indexCurrentDir = this.chronology.size() - 1;
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
        if (this.indexCurrentDir > 0) {
            this.indexCurrentDir--;
        }
    }

    @Override
    public String getCurrentDirectory() {
        return this.chronology.get(this.indexCurrentDir);
    }

}