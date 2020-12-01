package dayone

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FileLoaderTest {

    @Test
    internal fun `should load file correctly`() {
        val fileLoader = FileLoader()
        val integerList = fileLoader.createIntegerListFromFile("accounting-expenses-test.txt")
        assertEquals(integerList, arrayListOf(1721, 979, 366, 299, 675, 1456))
    }
}