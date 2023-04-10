package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public final class SafeNumberParserConfig {
    private final static Config config = ConfigFactory.load();

    public static int getBigDecimalMaxLength() {
        return config.getInt("safe-number-parser.big-decimal.max-length");
    }

    public static int getBigDecimalMaxScale() {
        return config.getInt("safe-number-parser.big-decimal.max-scale");
    }

    public static boolean allowBigIntegerENotation() {
        return config.getBoolean("safe-number-parser.big-integer.support-e-notation");
    }

    public static int getBigIntegerMaxLength() {
        return config.getInt("safe-number-parser.big-integer.max-length");
    }

    public static int getBigIntegerMaxScale() {
        return config.getInt("safe-number-parser.big-integer.max-scale");
    }

    public static int getDoubleMaxLength() {
        return config.getInt("safe-number-parser.double.max-length");
    }

    public static int getFloatMaxLength() {
        return config.getInt("safe-number-parser.float.max-length");
    }
}
