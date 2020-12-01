package dayone

import java.io.File
import java.net.URL
import java.nio.file.Paths

class FileLoader {
    fun createIntegerListFromFile(fileName: String): MutableList<Int> {
        val mutableIntegerList = mutableListOf<Int>()
        val res: URL = javaClass.classLoader.getResource(fileName)!!
        val file: File = Paths.get(res.toURI()).toFile()
        file.forEachLine { mutableIntegerList.add(it.toInt()) }
        return mutableIntegerList
    }
}
