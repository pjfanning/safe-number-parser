package com.github.pjfanning.safenumberparser;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestSafeBigInteger {
    @Test
    void testBigExponent() throws ConstraintException {
        String num = "2e308";
        SafeBigInteger sbi = new SafeBigInteger(num);
        assertEquals(new BigDecimal(num).toBigInteger(), sbi.toBigInteger());
    }

    @Test
    void testVeryBigExponent() throws ConstraintException {
        String num = "1e1010";
        assertThrows(ConstraintException.class, () -> new SafeBigInteger(num));
    }
}
