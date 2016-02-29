package fileminer.model;

import java.io.IOException;
import java.util.List;

import javax.swing.tree.TreePath;

public interface Bookmark {

    void addBookmark(List<TreePath> bookmarks) throws IOException;

    void removeBookmark(TreePath bookmark);

    public void readBookmark() throws IOException, ClassNotFoundException;

    public void writeBookmark() throws IOException;

}
