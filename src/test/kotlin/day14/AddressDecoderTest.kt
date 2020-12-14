package day14

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.paukov.combinatorics3.Generator.*
import java.lang.Exception

class AddressDecoderTest {
    val fileLoader = FileLoader()
    val input = fileLoader.createLinesFrom("day14/input.txt")

    @Test
    fun `part 1 solution`() {
        val memory = hashMapOf<Int, String>()
        var mask = "empty"
        input.forEach {
            if (it.startsWith("mask")) {
                mask = it.split(" ").last()
            } else {
                val (mem, newValue) = it.split(" = ")
                val parsedMem = mem.removePrefix("mem[").removeSuffix("]").toInt()
                val bitNewValue = newValue.toInt().to36bitString()
                val masked = bitNewValue.mapIndexed { index, c ->
                    if (mask[index] == 'X') {
                        c
                    } else {
                        mask[index]
                    }
                }.joinToString("")
                memory[parsedMem] = masked
            }
        }

        assertThat(memory.values.map { it.toLong(2) }.sum(), equalTo(7997531787333L))
    }

    @Test
    fun `part 2 solution`() {
        val memory = hashMapOf<Long, Long>()
        var mask = "empty"
        input.forEach { it ->
            if (it.startsWith("mask")) {
                mask = it.split(" ").last()
            } else {
                val (mem, newValue) = it.split(" = ")
                val parsedMem = mem.removePrefix("mem[").removeSuffix("]").toInt().to36bitString()
                val maskedValue = applyMask(parsedMem, mask)
                val permutations = permutation('0', '1').withRepetitions(maskedValue.filter { it == 'X' }.count())
                permutations.map { perm ->
                    val maskedValueArray = maskedValue.toCharArray()
                    var index = 0
                    maskedValueArray.indices.forEach { i ->
                        if (maskedValueArray[i] == 'X') {
                            maskedValueArray[i] = perm[index]
                            index++
                        }
                    }
                    maskedValueArray.joinToString("").toLong(2)
                }.forEach { memory[it] = newValue.toLong() }
            }
        }

        assertThat(memory.values.sum(), equalTo(3564822193820L))
    }
}

fun Int.to36bitString(): String = Integer.toBinaryString(this).padStart(36, '0')

private fun applyMask(bitNewValue: String, mask: String): String {
    return bitNewValue.mapIndexed { index, c ->
        when (mask[index]) {
            'X' -> 'X'
            '0' -> c
            '1' -> '1'
            else -> throw Exception("unknown value")
        }
    }.joinToString("")
}
