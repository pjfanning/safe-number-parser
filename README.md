[![Build (github)](https://github.com/pjfanning/safe-number-parser/actions/workflows/ci.yml/badge.svg)](https://github.com/pjfanning/safe-number-parser/actions/workflows/ci.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/safe-number-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pjfanning/safe-number-parser)
[![Javadoc](https://javadoc.io/badge/com.github.pjfanning/safe-number-parser.svg)](https://javadoc.io/doc/com.github.pjfanning/safe-number-parser)

# safe-number-parser
Java number parsing that applies configurable limits in order to avoid excessive resource use.

Number parsing has [subquadratic](https://en.wiktionary.org/wiki/subquadratic) performance. A malicious user might
send your application, a JSON file with a very long number to try to get your application to waste a lot of processing power.

There are also scenarios where numbers with only a few chars can cause serious processing issues.

This lib uses [com.typesafe:config](https://github.com/lightbend/config) lib to implement the configuration.
The default configs are implemented in [reference.conf](https://github.com/pjfanning/safe-number-parser/blob/main/src/main/resources/reference.conf).

## Implementation

The classes are:
* SafeDouble
* SafeFloat
* SafeBigDecimal
* SafeBigInteger

You can use these instead of the equivalent classes in the Java runtime. Because they extend `java.lang.Number`, most
JSON libraries (e.g. jackson-databind) will automatically treat these classes like numbers when serializing/deserializing.
Other libs that support formats like YAML and XML should work well too.

* The safety checks are only done when the String and/or char[] constructors are used.
* There are methods to return the Java equivalents. For instance, SafeDouble has a `toDouble()` method.
* You can create a SafeDouble from an existing Double instance, eg `new SafeDouble(new Double(1.23))` or `new SafeDouble(1.23)`.

If you find that serialization/deserialization of these classes does not work as expected,
it should be relatively easy to create custom serializers/deserializers.

## Jackson

These classes should work with legacy versions of Jackson. THey may not be as useful in Jackson v2.15 and above
because newer versions of Jackson have some of their own support for validating number sizes
(see [StreamReadConstraints](https://javadoc.io/static/com.fasterxml.jackson.core/jackson-core/2.15.0-rc2/com/fasterxml/jackson/core/StreamReadConstraints.html)).

Here is an [example](https://github.com/pjfanning/jackson-datatype-safe-number/blob/main/src/test/java/com/github/pjfanning/jackson/safenumber/TestPlainMapper.java) of using SafeBigDecimal with jackson-databind.

I may continue work on [jackson-datatype-safe-number](https://github.com/pjfanning/jackson-datatype-safe-number).
This provides custom serialization/deserialization for the types in this lib, that also takes advantage on jackson-core's
fast number parser support.
