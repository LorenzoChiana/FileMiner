package fileminer.view;

import java.io.File;
import java.net.URL;

public interface IResourceSupplier {

    void addElement(String fileName, URL fileURL) throws IllegalArgumentException;

    URL getURLByName(String fileName) throws IllegalArgumentException;

    void loadResourcesFromFile(File file) throws IllegalArgumentException;
}
