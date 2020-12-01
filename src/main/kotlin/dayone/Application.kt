package dayone


fun main(args: Array<String>) {
    val fileLoader = FileLoader()
    val integerList = fileLoader.createIntegerListFromFile("accounting-expenses-input.txt")

    val expenseAnomalyFinder = ExpenseAnomalyFinder()
    val anomalousValues = expenseAnomalyFinder.findAnomalousValues(integerList, 2020)
    println(expenseAnomalyFinder.findMultipleOfAnomalies(anomalousValues))
}


