package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeDouble {

    @Test
    void testNonInteger() throws ConstraintException {
        String num = "0.123";
        assertEquals(new Double(num), new SafeDouble(num).toDouble());
    }

    @Test
    void testMultiCharInteger() {
        String num = TestUtils.genLargeNumber();
        assertThrows(ConstraintException.class, () -> new SafeDouble(num));
    }

    @Test
    void testConfigWithSmallLengthLimit() {
        Config config = ConfigFactory.load("application-double-low-size-limit.conf");
        try {
            SafeNumberParserConfig.setConfig(config);
            final String num = "1234567890.123456789";
            assertThrows(ConstraintException.class, () -> new SafeDouble(num));
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }
}
