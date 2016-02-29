package fileminer.controller;

/**
 * 
 * @author Lorenzo Chiana
 *      enum with file system's basic operations.
 */
public enum Commands {
    /**
     * 
     */
    BACK,
    /**
     * 
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
     * OPEN = operation that open a file.
     */
    OPEN,
    /**
     * COMPRESS = operation that compress files.
     */
    COMPRESS,
    /**
     * DECOMPRESS = operation that decompress files.
     */
    DECOMPRESS;
}
