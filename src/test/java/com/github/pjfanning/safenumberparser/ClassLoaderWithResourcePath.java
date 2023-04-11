package com.github.pjfanning.safenumberparser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

public class ClassLoaderWithResourcePath extends ClassLoader {
    private final File directory;

    public ClassLoaderWithResourcePath(final ClassLoader parent, final File directory) {
        super(parent);
        if (directory == null) {
            throw new NullPointerException("directory must not be null");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("directory must be meet the condition java.io.File.#sDirectory()");
        }
        this.directory = directory;
    }

    @Override
    protected URL findResource(String name) {
        for (File file : directory.listFiles()) {
            if (name.equals(file.getName())) {
                try {
                    return file.toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    protected Enumeration<URL> findResources(String name) throws IOException {
        URL url = findResource(name);
        if (url == null) {
            return Collections.emptyEnumeration();
        }
        Vector<URL> vector = new Vector<>();
        vector.add(url);
        return vector.elements();
    }
}
