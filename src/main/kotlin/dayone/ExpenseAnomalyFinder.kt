package dayone

import org.paukov.combinatorics3.Generator


class ExpenseAnomalyFinder {
    fun findAnomalousValues(expenses: List<Int>, targetValue: Int, numberOfValues: Int): List<Int> {
        val expenseList = createSequenceOfCombinations(expenses, numberOfValues)
        return expenseList.filter { it.sum() == targetValue }.first()
    }

    fun findMultipleOfAnomalies(values: List<Int>): Int {
        return values.reduce{ acc, i ->  acc * i }
    }

    private fun <T> createSequenceOfCombinations(arr: List<T>, size: Int): Sequence<List<T>> = sequence {
        yieldAll(Generator.combination(arr).simple(size))
    }
}

