package ca.jonathanfritz.logutils.ca.jonathanfritz.logutils.cabrillo

import ca.jonathanfritz.logutils.Log
import ca.jonathanfritz.logutils.Parser
import ca.jonathanfritz.logutils.exception.MalformedLogException
import java.nio.file.Path
import java.util.*
import java.util.stream.Collectors
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.readLines

class CabrilloParser : Parser {

    @ExperimentalPathApi
    override fun read(path: Path): Log {
        val tags: Map<CabrilloTag, String> = path.readLines().stream()
            .map { line ->
                // figure out if the line starts with a known tag
                Arrays.stream(CabrilloTag.values())
                    .filter { t -> line.startsWith(t.tag) }
                    .findFirst()
                    .map {
                        // check if the tag value (the bit after the colon) matches the regex for that tag
                        val value = line.split(":")[1].trim()
                        if (it.pattern.matches(value)) {
                            Pair(it, value)
                        } else {
                            throw MalformedLogException("$line value does not match ${it.pattern.pattern}")
                        }
                    }
            }
            .filter { o -> o.isPresent }
            .map { o -> o.get() }
            .collect(Collectors.toMap(Pair<CabrilloTag, String>::first, Pair<CabrilloTag, String>::second))

        return CabrilloLog(tags)
    }

    override fun write(log: Log, path: Path) {
        TODO("Not yet implemented")
    }
}