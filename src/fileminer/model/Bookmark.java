package fileminer.model;

import java.io.IOException;
import java.util.List;

import javax.swing.tree.TreePath;

public interface Bookmark {

    void addBookmark(List<TreePath> bookmarks) throws IOException;

    void removeBookmark(List<TreePath> bookmark) throws IOException, ClassNotFoundException;

    public void readBookmark() throws IOException, ClassNotFoundException;

    public void writeBookmark() throws IOException;

	List<TreePath> getBookmarks();

}
