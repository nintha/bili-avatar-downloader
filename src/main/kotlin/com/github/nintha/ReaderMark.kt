package com.github.nintha

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths

class ReaderMark(val name: String) {
    companion object {
        const val PATH_PREFIX: String = "./"
    }

    private var mark: Long = 0
    private val markPath = Paths.get(PATH_PREFIX + name)

    init {
        val exists = Files.exists(markPath)
        if (!exists) {
            Files.createFile(markPath)
            Files.write(markPath, listOf("0"), Charset.forName("UTF-8"))
        }
        mark = Files.newBufferedReader(markPath).use { it.readLine()?.toLong() ?: 0 }
    }

    fun get(): Long = mark

    fun save(markValue: Long) {
        mark = markValue
        Files.write(markPath, listOf(markValue.toString()), Charset.forName("UTF-8"))
    }
}