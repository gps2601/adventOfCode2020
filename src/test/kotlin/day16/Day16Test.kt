package day16

import FileLoader
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class Day16Test {
    private val fileLoader = FileLoader()

    @Test
    fun `part 1 solution`() {
        val rules = fileLoader.createLinesFrom("day16/input.txt")
            .take(20)
            .map { it.split(": ")[1].split(" or ") }
            .flatten()
            .map {
                val (lowerBound, upperBound) = it.split("-")
                IntRange(lowerBound.toInt(), upperBound.toInt())
            }

        val tickets = fileLoader.createLinesFrom("day16/input.txt").subList(25, 269).map { it -> it.split(",").map { it.toInt() }.toMutableList() }
        val ticketErrors = mutableListOf<Int>()
        var invalidCounter = 0
        tickets.forEach { ticket ->
            var isValid = true
            ticket.forEach { ticketValue ->
                val numberOfRulesMet = rules.filter { rule -> rule.contains(ticketValue) }.count()
                if (numberOfRulesMet == 0) {
                    ticketErrors.add(ticketValue)
                    isValid = false
                }
            }
            if (!isValid) invalidCounter ++
        }
        println("invalid $invalidCounter")
        assertThat(ticketErrors.sum(), equalTo(23122))
    }

    @Test
    fun `part 2 solution`() {
        val rules = fileLoader.createLinesFrom("day16/input.txt").take(20).map { it.split(": ")[1].split(" or ") }.flatten().map {
                val (lowerBound, upperBound) = it.split("-")
                IntRange(lowerBound.toInt(), upperBound.toInt())
            }
        val tickets = fileLoader.createLinesFrom("day16/input.txt").subList(25, 269).map { it -> it.split(",").map { it.toInt() }.toMutableList() }
        val validTickets = tickets.filter { ticket ->
            ticket.filter { ticketValue ->
                val badField = rules.filter { it.contains(ticketValue) }.count() == 0
                badField
            }.count() == 0
        }
        assertThat(validTickets.size, equalTo(190))

        val allRules = fileLoader.createLinesFrom("day16/input.txt").take(20).map { it ->
            val (columnName, intrangeExpression) = it.split(": ")
            val ranges = intrangeExpression.split(" or ").map { it ->
                val (lower, upper) = it.split("-").map { it.toInt() }
                IntRange(lower, upper)
            }
            ColumnRule(columnName, ranges)
        }

        val possibleRulesByColumn = mutableListOf<MutableList<ColumnRule>>()
        for (i in 0 until validTickets[0].size) {
            possibleRulesByColumn.add(mutableListOf())
            allRules.forEach { possibleRulesByColumn[i].add(it) }
        }
        validTickets.forEach { ticket ->
            ticket.forEachIndexed { index, column ->
                allRules.forEach { rule ->
                    if(!(rule.ranges.first().contains(column) || rule.ranges.last().contains(column))){
                        possibleRulesByColumn[index].remove(rule)
                    }
                }
            }
        }
        val handledRules = mutableListOf<ColumnRule>()
        while(possibleRulesByColumn.sumBy { it.size } > 20) {
            val ruleWithOne = possibleRulesByColumn.find {
                it.size == 1 && !handledRules.contains(it[0])
            }?.first()!!
            handledRules.add(ruleWithOne)
            possibleRulesByColumn.forEach {
                if(it.size > 1) {
                    it.remove(ruleWithOne)
                }
            }
        }

        val myTicket = fileLoader.createLinesFrom("day16/input.txt")[22].split(",").map { it.toInt() }
        var product = 1L
        possibleRulesByColumn.forEachIndexed { index, rule ->
            if (rule.first().columnName.startsWith("departure")) {
                product *= myTicket[index]
            }
        }
        assertThat(product, equalTo(362974212989))
    }
}

data class ColumnRule(val columnName: String, val ranges: List<IntRange>)
