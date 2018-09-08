package com.github.nintha



class ZipUtil{
    companion object {

    }
}

//fun main(args: Array<String>) {
//    val path = Paths.get("zip.zip")
//    val fos = Files.newOutputStream(path)
//
//    val zip = ZipOutputStream(fos)
//
//    val entry = ZipEntry("test.txt")
//    zip.putNextEntry(entry)
//    zip.write("a simple txt".toByteArray())
//
//    val entry2 = ZipEntry("test2.txt")
//    zip.putNextEntry(entry2)
//    zip.write("a simple file2".toByteArray())
//
//    zip.closeEntry()
//    zip.close()
//}