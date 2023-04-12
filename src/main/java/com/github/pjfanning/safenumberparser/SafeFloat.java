package com.github.pjfanning.safenumberparser;

public class SafeFloat extends SafeNumber {
    private final Float f;

    public SafeFloat(final String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getFloatMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeFloat because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getFloatMaxLength());
        }
        f = new Float(input);
    }

    public SafeFloat(final Float f) {
        this.f = f;
    }

    public Float toFloat() {
        return f;
    }

    @Override
    public String toString() {
        return f.toString();
    }

    @Override
    public int intValue() {
        return f.intValue();
    }

    @Override
    public long longValue() {
        return f.longValue();
    }

    @Override
    public float floatValue() {
        return f.floatValue();
    }

    @Override
    public double doubleValue() {
        return f.doubleValue();
    }
}
