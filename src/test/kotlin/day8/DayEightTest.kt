package day8

import FileLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class DayEightTest {
    private val fileLoader = FileLoader()

    @Test
    internal fun `part 1 solution`() {
        val inputAsLines = fileLoader.createLinesFrom("day8/input.txt")
        val instructionMap = mutableMapOf<Int, CodeInstruction>()
        inputAsLines.forEachIndexed { index, line ->
            val (op, value) = line.split(" ")

            instructionMap[index] = CodeInstruction(index, op, value.toInt())
        }
        val instructionsSeen = mutableListOf<CodeInstruction>()

        var accumulator = 0
        var index = 0
        val output: Int
        while (true) {
            val currentInstruction = instructionMap[index]!!

            if (instructionsSeen.contains(currentInstruction)) {
                output = accumulator
                break
            }

            when (currentInstruction.operation) {
                "acc" -> {
                    accumulator += currentInstruction.value
                    index += 1
                }
                "jmp" -> {
                    index += currentInstruction.value
                    if (index < 0) {
                        index += (instructionMap.size - 1)
                    }
                }
                "nop" -> {
                    index += 1
                }
            }
            instructionsSeen.add(currentInstruction)
        }
        assertThat(output, equalTo(2034))
    }

    @Test
    internal fun `part 2 solution`() {
        val inputAsLines = fileLoader.createLinesFrom("day8/input.txt")
        val instructionMap = mutableMapOf<Int, CodeInstruction>()
        inputAsLines.forEachIndexed { index, line ->
            val (op, value) = line.split(" ")

            instructionMap[index] = CodeInstruction(index, op, value.toInt())
        }
        val iterator = instructionMap.filter { it.value.operation == "nop" || it.value.operation == "jmp" }.keys.iterator()
        var result: Int? = null
        var done = false
        while (!done) {
            val next = iterator.next()
            val mapForThisRun = mutableMapOf<Int, CodeInstruction>()
            instructionMap.forEach {
                mapForThisRun[it.key] = CodeInstruction(it.value.id, it.value.operation, it.value.value)
            }
            val instruction = mapForThisRun[next]!!
            when (instruction.operation) {
                "nop" -> instruction.operation = "jmp"
                "jmp" -> instruction.operation = "nop"
            }

            val instructionsSeen = mutableListOf<CodeInstruction>()
            var accumulator = 0
            var index = 0

            while (true) {
                val currentInstruction = mapForThisRun[index]!!

                if (instructionsSeen.contains(currentInstruction)) {
                    break
                }

                when (currentInstruction.operation) {
                    "acc" -> {
                        accumulator += currentInstruction.value
                        index += 1
                    }
                    "jmp" -> {
                        index += currentInstruction.value
                    }
                    "nop" -> {
                        index += 1
                    }
                }
                if (index < 0 || index >= instructionMap.size) {
                    done = true
                    result = accumulator
                    break
                }
                instructionsSeen.add(currentInstruction)
            }
        }
        assertThat(result, equalTo(672))
    }
}

data class CodeInstruction(val id: Int, var operation: String, val value: Int)
