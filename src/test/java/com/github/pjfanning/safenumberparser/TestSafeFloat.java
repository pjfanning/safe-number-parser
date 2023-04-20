package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeFloat {

    @Test
    void testNonInteger() throws ConstraintException {
        final String num = "1234567890.123456789";
        assertEquals(new Float(num), new SafeFloat(num).toFloat());
    }

    @Test
    void testMultiCharInteger() {
        String num = TestUtils.genLargeNumber();
        assertThrows(ConstraintException.class, () -> new SafeFloat(num));
    }

    @Test
    void testConfigWithSmallLengthLimit() {
        Config config = ConfigFactory.load("application-float-low-size-limit.conf");
        try {
            SafeNumberParserConfig.setConfig(config);
            final String num = "1234567890.123456789";
            assertThrows(ConstraintException.class, () -> new SafeFloat(num));
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }
}
