package fileminer.controller;

/**
 * 
 * @author Lorenzo Chiana
 *      enum with file system's basic operations.
 */
public enum Commands {
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
    MOVE,
    /**
     * LINK = operation that link files..
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
     * MODIFY = operation that modify a file.
     */
    MODIFY,
    /**
     * COMPRESS = operation that compress files.
     */
    COMPRESS,
    /**
     * DECOMPRESS = operation that decompress files.
     */
    DECOMPRESS;
}
