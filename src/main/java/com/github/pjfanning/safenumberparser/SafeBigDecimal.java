package com.github.pjfanning.safenumberparser;

import java.math.BigDecimal;

public class SafeBigDecimal implements SafeNumber {
    private final BigDecimal bigDecimal;

    public SafeBigDecimal(String input) throws ConstraintException {
        if (input.length() > SafeNumberParserConfig.getBigDecimalMaxLength()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigDecimal because the input is too long; max allowed chars is " +
                            SafeNumberParserConfig.getBigDecimalMaxLength());
        }
        bigDecimal = new BigDecimal(input);
        if (Math.abs(bigDecimal.scale()) > SafeNumberParserConfig.getBigDecimalMaxScale()) {
            throw new ConstraintException(
                    "Failed to parse SafeBigDecimal because the scale is too large; max allowed scale is " +
                            SafeNumberParserConfig.getBigDecimalMaxScale());
        }
    }

    public SafeBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public BigDecimal toBigDecimal() {
        return bigDecimal;
    }

    @Override
    public String toString() {
        return bigDecimal.toString();
    }
}
