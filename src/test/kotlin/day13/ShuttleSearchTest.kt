package day13

import FileLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ShuttleSearchTest {
    private val fileLoader = FileLoader()

    @Test
    fun `part 1 solution`() {
        val (earliestDepartureTime, busSchedule) = fileLoader.createLinesFrom("day13/input.txt")

        val earliestDepartTime = earliestDepartureTime.toInt()
        val answer = busSchedule
            .split(",")
            .filter { it != "x" }
            .map { it.toInt() }
            .map {
                var time = it
                do {
                    time += it
                } while (earliestDepartTime - time > 0)
                Pair((earliestDepartTime - time) * -1, it)
            }
            .minByOrNull { it.first }
            .let { it?.first?.times(it.second) }

        assertThat(answer, equalTo(1915))
    }

    @Test
    fun `part 2 solution`() {
        val (_, busSchedule) = fileLoader.createLinesFrom("day13/input.txt")
        val busses = busSchedule
            .split(",")
            .mapIndexed { index, busId ->
                Pair(busId, index)
            }
            .filter {
                it.first != "x"
            }
            .map {
                Bus(it.first.toLong(), it.second)
            }

        var step = busses[0].id
        var t = 0L
        for (i in 0..busses.size - 2) {
            val first = busses[i]
            val second = busses[i + 1]
            while (true) {
                val fitForFirst = (t + first.offset) % first.id == 0L
                val fitsForSecond = (t + second.offset) % second.id == 0L
                if (fitForFirst && fitsForSecond) {
                    step *= second.id
                    break
                } else {
                    t += step
                }
            }
        }

        assertThat(t, equalTo(294354277694107))
    }
}

data class Bus(val id: Long, val offset: Int)
