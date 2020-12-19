package day19

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import java.lang.Character.isDigit

class Day19Test {
    private val fileLoader = FileLoader()
    private val input = fileLoader.createInput("day19/input.txt")
        .split("\n\n").map { it.split("\n") }
    private val rules = input.first().associate {
        val (ruleNumber, rule) = it.split(":")
        Pair(ruleNumber.toInt(), rule.trim())
    }.toSortedMap()
    private val messages = input.last()

    @Test
    fun `part 1 solution`() {
        val regex = buildRegex()
        val valid = messages.filter { it.matches(regex) }.count()

        assertThat(valid, equalTo(178))
    }

    private fun buildRegex(): Regex {
        val finalRegex = "^${regexFrom(0)}$"
        return Regex(finalRegex)
    }

    private fun regexFrom(rule: Int): String {
        val value = rules[rule]!!.trim('"')
        if (value == "a" || value == "b") {
            return value
        }
        var regex = ""
        val parts = value.split(" ")
        for (part in parts) when {
            isDigit(part.toCharArray()[0]) -> regex += regexFrom(part.toInt())
            part == "|" -> regex += '|'
        }
        return "($regex)"
    }
}
