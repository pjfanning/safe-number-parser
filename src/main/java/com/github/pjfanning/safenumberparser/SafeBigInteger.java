package com.github.pjfanning.safenumberparser;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SafeBigInteger {
    private final BigInteger bigInteger;

    public SafeBigInteger(final String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getBigIntegerMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigInteger because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getBigIntegerMaxLength());
        }
        if (SafeNumberParserConfig.allowBigIntegerENotation()) {
            final BigDecimal bigDecimal = new BigDecimal(input);
            if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigIntegerMaxScale()) {
                throw new ConstraintException(
                        "Failed to parse SafeBigInteger because the scale is too big; max allowed scale is " +
                                SafeNumberParserConfig.getBigIntegerMaxScale());
            }
            bigInteger = bigDecimal.toBigInteger();
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
}
