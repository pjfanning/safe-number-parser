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

## Jackson

[jackson-datatype-safe-number](https://github.com/pjfanning/jackson-datatype-safe-number) provides custom serialization/deserialization
for the types in this lib.

It should be relatively easy to do something similar for your preferred JSON library (or for other libs supporting
structured data formats).
