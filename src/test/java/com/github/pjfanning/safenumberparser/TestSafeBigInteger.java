package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeBigInteger {

    @Test
    void testExponentWithDefaultConfig() {
        String num = "1e10";
        assertThrows(NumberFormatException.class, () -> new SafeBigInteger(num));
    }

    @Test
    void testBigExponent() throws Exception {
        Config config = createTestConfig();
        try {
            SafeNumberParserConfig.setConfig(config);
            String num = "2e308";
            SafeBigInteger sbi = new SafeBigInteger(num);
            assertEquals(new BigDecimal(num).toBigInteger(), sbi.toBigInteger());
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }

    @Test
    void testVeryBigExponent() throws Exception {
        Config config = createTestConfig();
        try {
            SafeNumberParserConfig.setConfig(config);
            String num = "1e1010";
            assertThrows(ConstraintException.class, () -> new SafeBigInteger(num));
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }

    private Config createTestConfig() throws URISyntaxException {
        final ClassLoader parent = TestClassLoader.class.getClassLoader();
        URL directoryUrl = parent.getResource("bigint-e-allowed");
        assertNotNull(directoryUrl, "directory resource found");
        File directory = Paths.get(directoryUrl.toURI()).toFile();
        ClassLoaderWithResourcePath classLoader = new ClassLoaderWithResourcePath(
                TestSafeBigInteger.class.getClassLoader(), directory);
        return ConfigFactory.defaultApplication(classLoader);
    }
}
