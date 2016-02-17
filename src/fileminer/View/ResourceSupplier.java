package fileminer.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class ResourceSupplier implements IResourceSupplier {

    private static ResourceSupplier SINGLETON = null;
    private final Map<String, URL> map;

	public ResourceSupplier() {
	    map = new HashMap<String, URL>();
	}

	public synchronized static ResourceSupplier getInstance() {
	    if (SINGLETON == null) {
	        SINGLETON = new ResourceSupplier();
	    }
	    return SINGLETON;
	}

    @Override
    public synchronized void addElement(final String fileName, final URL fileURL) throws IllegalArgumentException {
        final Optional<String> s = Optional.ofNullable(fileName);
        if (!s.isPresent()) {
            throw new IllegalArgumentException("Il nome non puo' essere null");
        }
        final Optional<URL> u = Optional.ofNullable(fileURL);
        if (!u.isPresent()) {
            throw new IllegalArgumentException("L'URL non puo' essere null");
        }
        map.put(s.get(), u.get());
    }

    @Override
    public URL getURLByName(final String fileName) throws IllegalArgumentException {
        final Optional<String> s = Optional.ofNullable(fileName);
        if (!s.isPresent()) {
            throw new IllegalArgumentException("Il nome non puo' essere null");
        }
        return map.get(s.get());
    }

    @Override
    public void loadResourcesFromFile(final File file) throws IllegalArgumentException {
        final Optional<File> f = Optional.ofNullable(file);
        if (!f.isPresent()) {
            throw new IllegalArgumentException("Il file non puo' essere null"); 
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(f.get()));

        } catch (Exception e) {
            
        }
    }

}
