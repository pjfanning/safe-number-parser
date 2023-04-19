package com.github.pjfanning.safenumberparser;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
}
