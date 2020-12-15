package day15

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun `part 1 solution`() {
        val startingNumbers = "1,0,16,5,17,4".split(",").map { it.toInt() }.toMutableList()
        val spokenNumbers = mutableListOf<Int>()

        while (spokenNumbers.size < 2020) {
            if (startingNumbers.isNotEmpty()) {
                spokenNumbers.add(startingNumbers.removeFirst())
            } else {
                val mostRecentlySpoken = spokenNumbers.last()
                if (onlySpokenOnce(spokenNumbers, mostRecentlySpoken)) {
                    spokenNumbers.add(0)
                } else {
                    val previousList = spokenNumbers.dropLast(1)
                    val lastIndexOf = previousList.lastIndexOf(mostRecentlySpoken)
                    val age = (spokenNumbers.size - 1) - lastIndexOf
                    spokenNumbers.add(age)
                }
            }
        }

        assertThat(spokenNumbers.last(), equalTo(1294))
    }

    @Test
    fun `part 2 solution`() {
        val spokenNumbers = mutableMapOf<Int, Int>()
        "1,0,16,5,17,4".split(",")
            .map { it.toInt() }
            .forEachIndexed { index, l -> spokenNumbers[l] = index }

        var count = spokenNumbers.size + 1
        var mostRecentlySpoken = 0
        while (count < 30000000L) {
            if (spokenNumbers.containsKey(mostRecentlySpoken)) {
                val nextSpoken = (count - 1) - spokenNumbers[mostRecentlySpoken]!!
                spokenNumbers[mostRecentlySpoken] = count - 1
                mostRecentlySpoken = nextSpoken
            } else {
                spokenNumbers[mostRecentlySpoken] = count - 1
                mostRecentlySpoken = 0
            }
            count++
        }
        assertThat(mostRecentlySpoken, equalTo(573522))
    }
}


private fun onlySpokenOnce(spokenNumbers: MutableList<Int>, mostRecentlySpoken: Int) = spokenNumbers.filter { it == mostRecentlySpoken }.count() == 1
