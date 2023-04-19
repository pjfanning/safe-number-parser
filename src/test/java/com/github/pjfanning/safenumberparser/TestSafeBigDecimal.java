package com.github.pjfanning.safenumberparser;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeBigDecimal {

    @Test
    void testValidExponentValue() throws ConstraintException {
        String num = "1e1000";
        assertEquals(new BigDecimal(num), new SafeBigDecimal(num).toBigDecimal());
    }

    @Test
    void testValidNegativeExponentValue() throws ConstraintException {
        String num = "-1e1000";
        assertEquals(new BigDecimal(num), new SafeBigDecimal(num).toBigDecimal());
    }

    @Test
    void testBigExponentValue() {
        String num = "1e1001";
        assertThrows(ConstraintException.class, () -> new SafeBigDecimal(num));
    }

    @Test
    void testBigNegativeExponentValue() {
        String num = "1e-1001";
        assertThrows(ConstraintException.class, () -> new SafeBigDecimal(num));
    }

    @Test
    void testNonInteger() throws ConstraintException {
        String num = "0.123";
        assertEquals(new BigDecimal(num), new SafeBigDecimal(num).toBigDecimal());
    }

    @Test
    void testMultiCharInteger() {
        String num = TestUtils.genLargeNumber();
        assertThrows(ConstraintException.class, () -> new SafeBigDecimal(num));
    }
}
