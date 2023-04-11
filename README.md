# safe-number-parser
Java number parsing that applies configurable limits in order to avoid excessive resource use.

Number parsing has [subquadratic](https://en.wiktionary.org/wiki/subquadratic) performance. A malicious user might
send your application, a JSON file with a very long number to try to get your application to waste a lot of processing power.

There are also scenarios where numbers with only a few chars can cause serious processing issues.

This lib uses [com.typesafe:config](https://github.com/lightbend/config) lib to implement the configuration.
The default configs are implemented in [reference.conf](https://github.com/pjfanning/safe-number-parser/blob/main/src/main/resources/reference.conf).
