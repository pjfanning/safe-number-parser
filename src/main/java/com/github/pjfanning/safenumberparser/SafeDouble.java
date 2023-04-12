package com.github.pjfanning.safenumberparser;

public class SafeDouble extends SafeNumber {
    private final Double d;

    public SafeDouble(final String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getDoubleMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeDouble because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getDoubleMaxLength());
        }
        d = new Double(input);
    }

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
}
