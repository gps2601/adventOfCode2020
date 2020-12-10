package day3

import FileLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.awt.Point

internal class TreeFinderTest {
    private val fileLoader = FileLoader()
    private val treeFinder = TreeFinder()

    @Test
    internal fun `part 1 solution`() {
        val input = fileLoader.createLinesFrom("day3/treemap.txt")

        val numberOfTreeCollisions = treeFinder.findNumberOfTreeCollisions(input, 3, 1)

        assertEquals(numberOfTreeCollisions, 294)
    }

    @Test
    internal fun `part 2 solution`() {
        val input = fileLoader.createLinesFrom("day3/treemap.txt")
        val parameterInput = listOf(
            Point(1, 1),
            Point(3, 1),
            Point(5, 1),
            Point(7, 1),
            Point(1, 2)
        )
        val numberOfTreeCollisions = parameterInput.map { treeFinder.findNumberOfTreeCollisions(input, it.x, it.y).toLong() }.reduce{ acc, i -> acc * (i) }

        assertEquals(numberOfTreeCollisions, 5774564250)
    }
}
