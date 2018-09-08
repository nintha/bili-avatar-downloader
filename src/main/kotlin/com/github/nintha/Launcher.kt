package com.github.nintha

import none.nintha.bilifetcher.util.HttpSender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom
import java.util.function.Supplier
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

val logger: Logger = LoggerFactory.getLogger("Launcher")
val pool: ExecutorService = Executors.newFixedThreadPool(100)
val urlPrefix = listOf(
        "http://i2.hdslb.com/bfs/face/",
        "http://i1.hdslb.com/bfs/face/",
        "http://i0.hdslb.com/bfs/face/")
const val lineLength = 45
val imageSize: String = AvatarProps["avatar.image.size"] ?: "16"
val storagePath: String = AvatarProps["avatar.storage.dir"] ?: "./storage"
val markName: String = AvatarProps["avatar.reader-mark.name"] ?: "readerMark"
val inputFilePath: String = AvatarProps["avatar.input.file-path"] ?: "./faces.csv"

fun readFile() {
    val readerMark = ReaderMark(markName)
    val resource = Paths.get(inputFilePath)
    val reader = Files.newBufferedReader(resource)

    var mark: Long = readerMark.get()
    logger.info("start: ${mark / lineLength}")
    reader.skip(mark)
    while (true) {
        val lines = (1..3000).map { reader.readLine() }.filter { it != null }.filter { it.isNotBlank() }
        if (lines.isEmpty()) break

        var tasks = lines
        val rslist: MutableList<Pair<ByteArray, String>> = mutableListOf()

        while (true) {
            if (tasks.isEmpty()) break

            val parts = tasks.distinct().map {
                val prefix = urlPrefix[ThreadLocalRandom.current().nextInt(urlPrefix.size)]
                val suffix = "@${imageSize}w_${imageSize}h.webp"
                Pair(CompletableFuture.supplyAsync(Supplier { HttpSender.download("$prefix$it$suffix") }, pool), it)
            }.map { Pair(it.first.get(), it.second) }.partition { it.first.isNotEmpty() }
            tasks = parts.second.map { it.second }

            rslist.addAll(parts.first)
        }

        val folder = Paths.get(storagePath)
        if (!Files.exists(folder)) Files.createDirectories(folder)
        val path = folder.resolve("$mark.zip")
        val fos = Files.newOutputStream(path)
        ZipOutputStream(fos).use { zip ->
            rslist.forEach {
                zip.putNextEntry(ZipEntry("${it.second}.webp"))
                zip.write(it.first)
            }
            zip.closeEntry()
        }

        mark += lines.map { it.length + 1 }.sum()
        readerMark.save(mark)
        logger.info("line: ${mark / lineLength}")

        Thread.sleep(1000)
    }
    reader.close()
}

fun main(args: Array<String>) {
    try {
        readFile()
    } catch (e: Exception) {
        println(">>>>>>>>>>>> read file error.")
        e.printStackTrace()
    }
    println(">>>>>>>>>>>> done.")
}