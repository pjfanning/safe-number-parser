package com.github.pjfanning.safenumberparser;

import java.util.Objects;

/**
 * A drop-in replacement for {@link Double} but with constraints applied when parsing.
 */
public class SafeDouble extends SafeNumber {
    private final Double d;

    /**
     * @param input number as text
     * @throws ConstraintException if a constraint fails
     * @throws NumberFormatException if the number format is invalid
     */
    public SafeDouble(final String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getDoubleMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeDouble because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getDoubleMaxLength());
        }
        d = new Double(input);
    }

    /**
     * @param d number to wrap, no validations are done
     */
    public SafeDouble(final Double d) {
        this.d = d;
    }

    public Double toDouble() {
        return d;
    }

    @Override
    public String toString() {
        return d.toString();
    }

    @Override
    public int intValue() {
        return d.intValue();
    }

    @Override
    public long longValue() {
        return d.longValue();
    }

    @Override
    public float floatValue() {
        return d.floatValue();
    }

    @Override
    public double doubleValue() {
        return d.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeDouble that = (SafeDouble) o;
        return Objects.equals(d, that.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(d);
    }
}
