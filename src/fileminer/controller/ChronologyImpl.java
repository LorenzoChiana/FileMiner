package fileminer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.TreePath;

/**
 * 
 * @author Lorenzo Chiana
 *
 */
public class ChronologyImpl implements Chronology {

    private final List<TreePath> chronology = new ArrayList<>();
    private Integer indexCurrentDir = this.chronology.size() - 1;
    
    @Override
    public void addDirectory(final TreePath path) {
        /*
         * - Se la directory corrente è l'ultima della cronologia,
         *      aggiungo la nuova directory in fondo alla cronologia.
         * - Altrimenti se è tornato indietro nella cronologia e sta accedendo
         *      alle stesse directory nella cronologia, avanzo solo nella cronologia.
         * - Altrimenti dal punto attuale creo un nuovo nodo nella cronologia
         */
        if (this.indexCurrentDir == this.chronology.size() - 1) {
            this.chronology.add(path);  
            this.indexCurrentDir = this.chronology.size() - 1;
        } else if (this.chronology.get(this.indexCurrentDir + 1).equals(path)) {
            nextDir();
        } else {
            this.chronology.set(this.indexCurrentDir + 1, path);
            final Iterator<TreePath> it = this.chronology.listIterator(this.indexCurrentDir + 2);
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            this.indexCurrentDir = this.chronology.size() - 1;
        }
    }

    @Override
    public List<TreePath> getChronology() {
        return this.chronology;
    }

    @Override
    public void nextDir() {
        if (this.indexCurrentDir < this.chronology.size() - 1) {
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
    public TreePath getCurrentDirectory() {
        return this.chronology.get(this.indexCurrentDir);
    }

}