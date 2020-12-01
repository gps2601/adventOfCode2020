package dayone

class ExpenseAnomalyFinder {
    fun findAnomalousValues(expenses: List<Int>, targetValue: Int): List<Int> {
        val expensePairs = createPairsFrom(expenses)
        return expensePairs.filter { (a, b) -> a + b == targetValue }.first().toList()
    }

    fun findMultipleOfAnomalies(values: List<Int>): Int {
        return values.reduce{ acc, i ->  acc * i }
    }

    private fun <T> createPairsFrom(arr: List<T>): Sequence<Pair<T, T>> = sequence {
        for(i in 0 until arr.size-1)
            for(j in i+1 until arr.size)
                yield(arr[i] to arr[j])
    }
}
