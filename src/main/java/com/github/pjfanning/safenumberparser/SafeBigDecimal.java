package com.github.pjfanning.safenumberparser;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A drop-in replacement for {@link BigDecimal} but with constraints applied when parsing.
 */
public class SafeBigDecimal extends SafeNumber {
    private final BigDecimal bigDecimal;

    /**
     * @param input number as text
     * @throws ConstraintException if a constraint fails
     * @throws NumberFormatException if the number format is invalid
     */
    public SafeBigDecimal(final String input) throws ConstraintException {
        this(input.toCharArray(), 0, input.length());
    }

    /**
     * @param array number as char array
     * @throws ConstraintException if a constraint fails
     * @throws NumberFormatException if the number format is invalid
     */
    public SafeBigDecimal(final char[] array) throws ConstraintException {
        this(array, 0, array.length);
    }

    /**
     * @param array number as char array
     * @param offset position in array to start reading from
     * @param len number of chars to read
     * @throws ConstraintException if a constraint fails
     * @throws NumberFormatException if the number format is invalid
     */
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

    /**
     * @param bigDecimal number to wrap, no validations are done
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeBigDecimal that = (SafeBigDecimal) o;
        return Objects.equals(bigDecimal, that.bigDecimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bigDecimal);
    }
}
