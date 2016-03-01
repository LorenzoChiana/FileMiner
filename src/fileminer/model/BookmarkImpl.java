package fileminer.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreePath;

public class BookmarkImpl implements Bookmark{

    private List<TreePath> bookmarks = new ArrayList<>();
    private File file;
    private static final String NAME = "bookmarks.txt";

    
    public BookmarkImpl() {
        file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + NAME);
        try {
        	if (file.exists()) {
        		this.readBookmark();
			} else {
				this.createFile();
				this.writeBookmark();
			}
    	} catch (ClassNotFoundException | IOException e) {
    		e.printStackTrace();
    	}
    }

    @Override
    public void addBookmark(final List<TreePath> bookmarks) throws IOException {

        for (final TreePath bookmark: bookmarks) {
            this.bookmarks.add(bookmark);
        }
        
        this.writeBookmark();
        
    }

    @Override
    public void removeBookmark(final List<TreePath> bookmarks) throws IOException, ClassNotFoundException {
    	if (!bookmarks.isEmpty()) {
	    	for (final TreePath bookmark : bookmarks) {
		        if (this.bookmarks.contains(bookmark)) {
		            this.bookmarks.remove(this.bookmarks.indexOf(bookmark));
		        } else {
		            throw new NullPointerException();
		        }
	    	}
            this.deleteFile();
            this.createFile();
            this.writeBookmark();
    	}
    }

	@SuppressWarnings("unchecked")
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
    
    @Override
    public List<TreePath> getBookmarks() {
        return this.bookmarks;
    }
    
    
    private void createFile() throws IOException {
        file.createNewFile();
    }

    private void deleteFile() throws IOException {
    	file.delete();
    }
}
