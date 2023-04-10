package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class SafeNumberParserConfig {
    private final static Config config = ConfigFactory.load();

    static int getBigDecimalMaxLength() {
        return config.getInt("safe-number-parser.big-decimal.max-length");
    }

    static int getBigDecimalMaxScale() {
        return config.getInt("safe-number-parser.big-decimal.max-scale");
    }

    static boolean allowBigIntegerENotation() {
        return config.getBoolean("safe-number-parser.big-integer.support-e-notation");
    }

    static int getBigIntegerMaxLength() {
        return config.getInt("safe-number-parser.big-integer.max-length");
    }

    static int getBigIntegerMaxScale() {
        return config.getInt("safe-number-parser.big-integer.max-scale");
    }

    static int getDoubleMaxLength() {
        return config.getInt("safe-number-parser.double.max-length");
    }

    static int getFloatMaxLength() {
        return config.getInt("safe-number-parser.float.max-length");
    }
}
