package day9

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class EncodingErrorTest {
    private val fileLoader = FileLoader()
    private val encoder = Encoder()

    @Test
    fun `example input part 1`() {
        val preambleSize = 5
        val input = fileLoader.createLinesFrom("day9/example-input.txt").map { it.toLong() }

        val firstFault = encoder.findFirstInvalid(preambleSize, input)

        assertThat(firstFault, equalTo(127))
    }

    @Test
    fun `part 1 solution`() {
        val preambleSize = 25
        val input = fileLoader.createLinesFrom("day9/input.txt").map { it.toLong() }

        val firstFault = encoder.findFirstInvalid(preambleSize, input)

        assertThat(firstFault, equalTo(530627549))
    }

    @Test
    fun `example input part 2`() {
        val input = fileLoader.createLinesFrom("day9/example-input.txt").map { it.toLong() }

        val contiguousSet:List<Long> = encoder.findContiguousSetEqualTo(127L, input)

        assertThat(contiguousSet.minOrNull(), equalTo(15L))
        assertThat(contiguousSet.maxOrNull(), equalTo(47L))
        assertThat(contiguousSet.sum(), equalTo(127L))
    }

    @Test
    fun `part 2 solution`() {
        val input = fileLoader.createLinesFrom("day9/input.txt").map { it.toLong() }

        val contiguousSet:List<Long> = encoder.findContiguousSetEqualTo(530627549L, input)

        val minOrNull = contiguousSet.minOrNull()!!
        val maxOrNull = contiguousSet.maxOrNull()!!
        val sum = minOrNull + maxOrNull
        assertThat(sum, equalTo(77730285))
    }
}