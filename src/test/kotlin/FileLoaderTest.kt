import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FileLoaderTest {

    @Test
    internal fun `should load file as list of integers correctly`() {
        val fileLoader = FileLoader()
        val integerList = fileLoader.createIntegerListFromFile("dayone/accounting-expenses-test.txt")
        assertEquals(integerList, arrayListOf(1721, 979, 366, 299, 675, 1456))
    }

    @Test
    internal fun `should load file as list of strings correctly`() {
        val fileLoader = FileLoader()
        val stringList = fileLoader.createLinesFrom("daytwo/password-test.txt")
        assertThat(stringList, containsInAnyOrder("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"))
    }
}