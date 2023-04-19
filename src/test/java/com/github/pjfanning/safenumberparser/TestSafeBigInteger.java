package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeBigInteger {

    @Test
    void testExponentValue() {
        String num = "1e10";
        assertThrows(NumberFormatException.class, () -> new SafeBigInteger(num));
    }

    @Test
    void testNonInteger() {
        String num = "0.123";
        assertThrows(NumberFormatException.class, () -> new SafeBigInteger(num));
    }

    @Test
    void testBigExponentWithENotationAllowed() throws Exception {
        Config config = ConfigFactory.load("application-bigint-e-notation.conf");
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
    void testVeryBigExponentWithENotationAllowed() throws Exception {
        Config config = ConfigFactory.load("application-bigint-e-notation.conf");
        try {
            SafeNumberParserConfig.setConfig(config);
            String num = "1e1010";
            assertThrows(ConstraintException.class, () -> new SafeBigInteger(num));
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }

    @Test
    void testNonIntegerWithENotationAllowed() throws Exception {
        Config config = ConfigFactory.load("application-bigint-e-notation.conf");
        try {
            SafeNumberParserConfig.setConfig(config);
            String num = "0.123";
            assertThrows(ArithmeticException.class, () -> new SafeBigInteger(num));
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }

    @Test
    void testNonIntegerWithENotationAndInexactAllowed() throws Exception {
        Config config = ConfigFactory.load("application-bigint-e-notation-inexact.conf");
        try {
            SafeNumberParserConfig.setConfig(config);
            String num = "0.123";
            SafeBigInteger sbi = new SafeBigInteger(num);
            assertEquals(BigInteger.ZERO, sbi.toBigInteger());
        } finally {
            SafeNumberParserConfig.setConfig(null);
        }
    }

    @Test
    void testMultiCharInteger() {
        String num = TestUtils.genLargeNumber();
        assertThrows(ConstraintException.class, () -> new SafeBigInteger(num));
    }
}
