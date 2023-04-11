package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClassLoader {
    @Test
    void testLoader() throws URISyntaxException {
        final ClassLoader parent = TestClassLoader.class.getClassLoader();
        URL directoryUrl = parent.getResource("bigint-e-allowed");
        assertNotNull(directoryUrl, "directory resource found");
        File directory = Paths.get(directoryUrl.toURI()).toFile();
        ClassLoaderWithResourcePath classLoader = new ClassLoaderWithResourcePath(
                parent, directory);
        URL fileUrl = classLoader.getResource("application.conf");
        assertNotNull(fileUrl, "file resource found");
        Config config = ConfigFactory.defaultApplication(classLoader);
        assertTrue(config.getBoolean("safe-number-parser.big-integer.support-e-notation"));
    }
}
