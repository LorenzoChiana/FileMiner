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
    /*
     * da implementare meglio.
     */
    @Override
    public void addDirectory(final String path) {
        this.chronology.add(path);
    }

    @Override
    public List<String> getChronology() {
        return this.chronology;
    }

}
