package dayone

import FileLoader
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExpenseAnomalyFinderTest {
    private val expenseAnomalyFinder = ExpenseAnomalyFinder()
    private val fileLoader = FileLoader()

    @Test
    internal fun `should find list of values that sum to target value`() {
        val expenses = arrayListOf(1721, 979, 366, 299, 675, 1456)
        val targetValue = 2020

        val actual = expenseAnomalyFinder.findAnomalousValues(expenses, targetValue, 2)

        assertThat(actual, containsInAnyOrder(299, 1721))
    }

    @Test
    internal fun `should multiply list of values together`() {
        assertEquals(514579, expenseAnomalyFinder.multipleOfAnomalies(listOf(1721, 299)))
    }

    @Test
    internal fun `part 1 solution`() {
        val integerList = fileLoader.createIntegerListFromFile("dayone/accounting-expenses-input.txt")
        val anomalousValues = expenseAnomalyFinder.findAnomalousValues(integerList, 2020, 2)
        val solution = expenseAnomalyFinder.multipleOfAnomalies(anomalousValues)

        assertThat(solution, equalTo(989824))
    }

    @Test
    internal fun `should find n sized list of values that sum to target value`() {
        val expenses = arrayListOf(1721, 979, 366, 299, 675, 1456)
        val targetValue = 2020
        val n = 3

        val actual = expenseAnomalyFinder.findAnomalousValues(expenses, targetValue, n)

        assertThat(actual, containsInAnyOrder(979, 366, 675))
    }

    @Test
    internal fun `part 2 solution`() {
        val integerList = fileLoader.createIntegerListFromFile("dayone/accounting-expenses-input.txt")
        val expenseAnomalyFinder = ExpenseAnomalyFinder()
        val anomalousValues = expenseAnomalyFinder.findAnomalousValues(integerList, 2020, 3)
        val solution = expenseAnomalyFinder.multipleOfAnomalies(anomalousValues)

        assertThat(solution, equalTo(66432240))
    }
}
