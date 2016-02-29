package fileminer.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.tree.TreePath;

public class BookmarkImpl implements Bookmark{

    List<TreePath> bookmarks;
    File file;
    private static final String NAME = "FileMiner.txt";

    
    public BookmarkImpl() throws IOException, ClassNotFoundException {
        file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + NAME);
        if (file.exists()) {
            this.readBookmark();
        } else {
            this.createFile();
        }      
    }

    @Override
    public void addBookmark(final List<TreePath> bookmarks) throws IOException {

        for (TreePath bookmark: bookmarks) {
            this.bookmarks.add(bookmark);
        }
        
        this.writeBookmark();
        
    }

    @Override
    public void removeBookmark(final TreePath bookmark) {
        if (this.bookmarks.contains(bookmark)) {
            this.bookmarks.remove(this.bookmarks.indexOf(bookmark));
        } else {
            throw new NullPointerException();
        }
    }

    
    @Override
    public void readBookmark() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.bookmarks = (List<TreePath>) ois.readObject();
        ois.close();
        fis.close();
    }

    @Override
    public void writeBookmark() throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bookmarks);
        oos.close();
        fos.close();
    }
    
    
    public List<TreePath> getBookmarks() {
        return this.bookmarks;
    }
    
    
    private void createFile() throws IOException {
        file.createNewFile();
        
    } 
}
