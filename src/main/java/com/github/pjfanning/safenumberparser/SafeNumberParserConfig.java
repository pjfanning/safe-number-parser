package com.github.pjfanning.safenumberparser;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Used to evaluate the configuration used by this library. See
 * <a href="https://github.com/lightbend/config">typesafe/config</>
 * for documentation on how to override these configs.
 *
 * The default config is set in
 * <a href="https://github.com/pjfanning/safe-number-parser/blob/main/src/main/resources/reference.conf">reference.conf</a>.
 */
public final class SafeNumberParserConfig {
    private static final Config defaultConfig = ConfigFactory.load();
    private static Config config = defaultConfig;

    // open for testing
    static void setConfig(final Config configOverride) {
        if (configOverride == null) {
            config = defaultConfig;
        } else {
            config = configOverride.withFallback(defaultConfig);
        }
    }

    public static int getBigDecimalMaxLength() {
        return config.getInt("safe-number-parser.big-decimal.max-length");
    }

    public static int getBigDecimalMaxScale() {
        return config.getInt("safe-number-parser.big-decimal.max-scale");
    }

    public static boolean isBigIntegerENotationSupported() {
        return config.getBoolean("safe-number-parser.big-integer.support-e-notation");
    }

    public static boolean isBigIntegerExactConversionRequired() {
        return config.getBoolean("safe-number-parser.big-integer.enforce-exact");
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
