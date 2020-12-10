package day9

import java.lang.Exception

class Encoder {

    fun findFirstInvalid(preambleSize: Int, input: List<Long>): Long {
        var currentPosition = preambleSize

        while (true) {
            val precedingValues = input.subList(currentPosition - preambleSize, currentPosition)
            val currentValue = input[currentPosition]
            if (precedingContainsPairFor(precedingValues, currentValue)) {
                currentPosition++
            } else {
                break
            }
        }

        return input[currentPosition]
    }

    private fun precedingContainsPairFor(precedingValues: List<Long>, currentValue: Long): Boolean {
        val pairs = createPairs(precedingValues)
        return pairs.any {
            it.first + it.second == currentValue
        }
    }

    private fun createPairs(precedingValues: List<Long>): List<Pair<Long, Long>> {
        val pairs = mutableListOf<Pair<Long, Long>>()
        for (i in precedingValues.indices) {
            for (j in precedingValues.indices) {
                val pair1 = precedingValues[i]
                val pair2 = precedingValues[j]
                if (pair1 != pair2) {
                    pairs.add(Pair(pair1, pair2))
                }
            }
        }
        return pairs
    }

    fun findContiguousSetEqualTo(targetValue: Long, input: List<Long>): List<Long> {
        input.indices.forEach { i ->
            (i+1 until input.size).forEach { j ->
                val subList = input.subList(i, j)
                if (subList.sum() == targetValue) {
                    return subList
                }
            }
        }
        throw Exception("could not find any set :(")
    }
}
