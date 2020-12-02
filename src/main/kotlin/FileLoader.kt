import java.io.File
import java.net.URL
import java.nio.file.Paths

class FileLoader {
    fun createIntegerListFromFile(fileName: String): MutableList<Int> {
        val file: File = createFile(fileName)
        val mutableIntegerList = mutableListOf<Int>()
        file.forEachLine { mutableIntegerList.add(it.toInt()) }
        return mutableIntegerList
    }

    fun createLinesFrom(fileName: String): List<String> {
        return createFile(fileName).readLines()
    }

    private fun createFile(fileName: String): File {
        val res: URL = javaClass.classLoader.getResource(fileName)!!
        return Paths.get(res.toURI()).toFile()
    }
}
