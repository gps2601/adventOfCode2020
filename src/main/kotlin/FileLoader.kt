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

    fun createLongListFromFile(fileName: String): MutableList<Long> {
        val file: File = createFile(fileName)
        val mutableIntegerList = mutableListOf<Long>()
        file.forEachLine { mutableIntegerList.add(it.toLong()) }
        return mutableIntegerList
    }

    fun createLinesFrom(fileName: String): List<String> {
        return createFile(fileName).readLines()
    }

    fun createLinesFromDoubleLineBreaked(fileName: String): List<String> {
        return createFile(fileName).readText().split("\n\n")
    }

    fun createInput(fileName: String): String {
        return createFile(fileName).readText()
    }

    private fun createFile(fileName: String): File {
        val res: URL = javaClass.classLoader.getResource(fileName)!!
        return Paths.get(res.toURI()).toFile()
    }
}
