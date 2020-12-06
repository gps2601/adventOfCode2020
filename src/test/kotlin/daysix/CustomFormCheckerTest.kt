package daysix

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class CustomFormCheckerTest {
    private val fileLoader = FileLoader()

    @Test
    internal fun `part 1 example input`() {
        val input = fileLoader.createLinesFromDoubleLineBreaked("daysix/test-input.txt")
        val allAnswersByGroup = input.map { it.replace("\n", "") }
        val uniqueAnswersPerGroup = allAnswersByGroup.map { it.toSet().count() }
        val sum = uniqueAnswersPerGroup.sum()

       assertThat(sum, equalTo(11))
    }

    @Test
    internal fun `part 1 solution`() {
        val input = fileLoader.createLinesFromDoubleLineBreaked("daysix/answer-input.txt")
        val allAnswersByGroup = input.map { it.replace("\n", "") }
        val uniqueAnswersPerGroup = allAnswersByGroup.map { it.toSet().count() }
        val sum = uniqueAnswersPerGroup.sum()

        assertThat(sum, equalTo(6625))
    }

    @Test
    internal fun `part 2 example input`() {
        val input = fileLoader.createLinesFromDoubleLineBreaked("daysix/test-input.txt")

        val everyoneYes: List<Int> = commonAnswersInEachGroup(input)
        assertThat(everyoneYes.sum(), equalTo(6))
        assertThat(everyoneYes[0], equalTo(3))
        assertThat(everyoneYes[1], equalTo(0))
        assertThat(everyoneYes[2], equalTo(1))
        assertThat(everyoneYes[3], equalTo(1))
        assertThat(everyoneYes[4], equalTo(1))
    }

    @Test
    internal fun `part 2 solution`() {
        val input = fileLoader.createLinesFromDoubleLineBreaked("daysix/answer-input.txt")

        val everyoneYes: List<Int> = commonAnswersInEachGroup(input)
        assertThat(everyoneYes.sum(), equalTo(3360))
    }

    private fun commonAnswersInEachGroup(input: List<String>): List<Int> {
        return input.map { it.split("\n").map { it.toSet() }.reduce(Set<Char>::intersect).size }
    }
}