package fileminer.controller;

/**
 * 
 * @author Lorenzo Chiana
 *      enum with file system's basic operations.
 */
public enum Commands {
    /**
     * BACK = operation that set the current directory to an old visited one.
     */
    BACK,
    /**
     * NEXT = operation that set the current directory to a recent visited one.
     */
    NEXT,
    /**
     * COPY = operation that copy files.
     */
    COPY,
    /**
     * PASTE = operation that paste.
     */
    PASTE,
    /**
     * CUT = operation that cut files.
     */
    CUT,
    /**
     * DELETE = operation that remove files.
     */
    DELETE,
    /**
     * DELETE_BOOKMARK = operation that open a dialog view to choose what bookmark should be deleted.
     */
    DELETE_BOOKMARK,
    /**
     * NEW_FILE = operation that create a new file.
     */
    NEW_FILE,
    /**
     * NEW_DIR = operation that create a new directory.
     */
    NEW_DIR,
    /**
     * NEW_LINK = operation that create a new link.
     */
    NEW_LINK,
    /**
     * NEW_BOOKMARK = operation that create a new bookmark of the selected item.
     */
    NEW_BOOKMARK,
    /**
     * OPEN = operation that open a file.
     */
    OPEN,
    /**
     * OPEN_BOOKMARK = operation that open a dialog view of saved bookmarks.
     */
    OPEN_BOOKMARK,
    /**
     * REFRESH = operation that refresh the current directory.
     */
    REFRESH,
    /**
     * ZIP = operation that compress files into ".zip".
     */
    ZIP,
    /**
     * UNZIP = operation that decompress files from ".zip".
     */
    UNZIP;
}
