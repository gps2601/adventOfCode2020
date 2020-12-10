package day10

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class DayTenTest {
    private val fileLoader = FileLoader()

    @Test
    @Disabled
    fun `part 1 example`() {
        val input = fileLoader.createLinesFrom("day10/example.txt")

        assertThat(1, equalTo(2))
    }

    @Test
    @Disabled
    fun `part 1 solution`() {
        val input = fileLoader.createLinesFrom("day10/input.txt")

        assertThat(1, equalTo(2))
    }

    @Test
    @Disabled
    fun `part 2 example`() {
        val input = fileLoader.createLinesFrom("day10/example.txt")

        assertThat(1, equalTo(2))
    }

    @Test
    @Disabled
    fun `part 2 solution`() {
        val input = fileLoader.createLinesFrom("day10/input.txt")

        assertThat(1, equalTo(2))
    }
}
