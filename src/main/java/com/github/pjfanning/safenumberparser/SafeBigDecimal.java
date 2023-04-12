package com.github.pjfanning.safenumberparser;

import java.math.BigDecimal;

public class SafeBigDecimal extends SafeNumber {
    private final BigDecimal bigDecimal;

    public SafeBigDecimal(final String input) throws ConstraintException {
        this(input.toCharArray(), 0, input.length());
    }

    public SafeBigDecimal(final char[] array) throws ConstraintException {
        this(array, 0, array.length);
    }

    public SafeBigDecimal(final char[] array, final int offset, final int len) throws ConstraintException {
        if (len > SafeNumberParserConfig.getBigDecimalMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigDecimal because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getBigDecimalMaxLength());
        }
        bigDecimal = new BigDecimal(array, offset, len);
        if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigDecimalMaxScale()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigDecimal because the scale is too large; max allowed scale is " +
                            SafeNumberParserConfig.getBigDecimalMaxScale());
        }
    }

    public SafeBigDecimal(final BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public BigDecimal toBigDecimal() {
        return bigDecimal;
    }

    @Override
    public String toString() {
        return bigDecimal.toString();
    }

    @Override
    public int intValue() {
        return bigDecimal.intValue();
    }

    @Override
    public long longValue() {
        return bigDecimal.longValue();
    }

    @Override
    public float floatValue() {
        return bigDecimal.floatValue();
    }

    @Override
    public double doubleValue() {
        return bigDecimal.doubleValue();
    }
}
