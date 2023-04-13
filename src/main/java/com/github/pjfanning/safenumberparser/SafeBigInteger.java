package com.github.pjfanning.safenumberparser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class SafeBigInteger extends SafeNumber {
    private final BigInteger bigInteger;

    public SafeBigInteger(final String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getBigIntegerMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigInteger because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getBigIntegerMaxLength());
        }
        if (SafeNumberParserConfig.isBigIntegerENotationSupported()) {
            final BigDecimal bigDecimal = new BigDecimal(input);
            if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigIntegerMaxScale()) {
                throw new ConstraintException(
                        "Failed to parse SafeBigInteger because the scale is too big; max allowed scale is " +
                                SafeNumberParserConfig.getBigIntegerMaxScale());
            }
            bigInteger = SafeNumberParserConfig.isBigIntegerExactConversionRequired() ?
                    bigDecimal.toBigIntegerExact() : bigDecimal.toBigInteger();
        } else {
            bigInteger = new BigInteger(input);
        }
    }

    public SafeBigInteger(final BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    public BigInteger toBigInteger() {
        return bigInteger;
    }

    @Override
    public String toString() {
        return bigInteger.toString();
    }

    @Override
    public int intValue() {
        return bigInteger.intValue();
    }

    @Override
    public long longValue() {
        return bigInteger.longValue();
    }

    @Override
    public float floatValue() {
        return bigInteger.floatValue();
    }

    @Override
    public double doubleValue() {
        return bigInteger.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeBigInteger that = (SafeBigInteger) o;
        return Objects.equals(bigInteger, that.bigInteger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bigInteger);
    }
}
