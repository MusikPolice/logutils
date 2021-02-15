package ca.jonathanfritz.logutils

import java.nio.file.Path

interface Parser {
    fun read(path: Path): Log
    fun write(log: Log, path: Path)
}