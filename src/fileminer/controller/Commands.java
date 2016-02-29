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
     * MOVE = operation that move files into other directory.
     */
    LINK,
    /**
     * DELETE = operation that remove files..
     */
    DELETE,
    /**
     * NEW = operation that create new file.
     */
    NEW,
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
