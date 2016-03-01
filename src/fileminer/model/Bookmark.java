package fileminer.model;

import java.io.IOException;
import java.util.List;

import javax.swing.tree.TreePath;

/**
 * @author Daniele
 * Interface to manage bookmarks.
 */
public interface Bookmark {

    /**
     * Add bookmark in the list.
     * @param bookmarks
     * @throws IOException
     */
    void addBookmark(List<TreePath> bookmarks) throws IOException;

    /**
     * Remove bookmarks from list.
     * @param bookmark
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void removeBookmark(List<TreePath> bookmark) throws IOException, ClassNotFoundException;

    /**
     * Read bookmarks from files.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void readBookmark() throws IOException, ClassNotFoundException;

    /**
     * Write bookmarks on file.
     * @throws IOException
     */
    void writeBookmark() throws IOException;

	/**
	 * Get bookmarks.
	 * @return bookmarks
	 */
	List<TreePath> getBookmarks();

}
