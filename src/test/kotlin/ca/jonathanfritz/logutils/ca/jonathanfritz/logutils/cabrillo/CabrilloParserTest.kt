package ca.jonathanfritz.logutils.ca.jonathanfritz.logutils.cabrillo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi

internal class CabrilloParserTest {

    private val parser = CabrilloParser()

    @Test
    @ExperimentalPathApi
    fun testParseValidTags() {
        val path = readFile("cabrillo/valid-version-3.0.log")
        val log = parser.read(path) as CabrilloLog
        assertEquals("3.0", log.tags[CabrilloTag.StartOfLog])
        assertEquals("VA3JFZ", log.tags[CabrilloTag.Callsign])
        assertEquals("CQ-WPX-SSB", log.tags[CabrilloTag.Contest])
        assertEquals("NON-ASSISTED", log.tags[CabrilloTag.CategoryAssisted])
        assertEquals("", log.tags[CabrilloTag.EndOfLog])
    }

    private fun readFile(filename: String) : Path {
        return Path.of(getResource(filename)!!.toURI()).toAbsolutePath()
    }

    // stolen from https://github.com/krosenvold/struts2/blob/master/xwork-core/src/main/java/com/opensymphony/xwork2/util/ClassLoaderUtil.java
    private fun getResource(resourceName: String): URL? {
        var url: URL? = Thread.currentThread().contextClassLoader.getResource(resourceName)
        if (url == null) {
            url = CabrilloParserTest::class.java.classLoader.getResource(resourceName)
        }
        if (url == null) {
            val cl = CabrilloParserTest::class.java.classLoader
            if (cl != null) {
                url = cl.getResource(resourceName)
            }
        }
        return if (url == null && (resourceName.isEmpty() || resourceName[0] != '/')) {
            getResource("/$resourceName")
        } else url
    }
}