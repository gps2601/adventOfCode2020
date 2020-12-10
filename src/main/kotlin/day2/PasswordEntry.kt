package day2

class PasswordEntry(passwordEntryInput: String) {
    val range: IntRange
    val firstPosition: Int
    val secondPosition: Int
    val pattern: Char
    val password: String

    private val arrayIndexOffset = 1

    init {
        val (validationVariablesInput, passwordPattern, passwordInput) = passwordEntryInput.split(" ")
        val (firstVariable, secondVariable) = validationVariablesInput.split("-").map { it.toInt() }
        range = IntRange(firstVariable, secondVariable)
        firstPosition = firstVariable - arrayIndexOffset
        secondPosition = secondVariable - arrayIndexOffset
        pattern = passwordPattern.removeSuffix(":").single()
        password = passwordInput
    }
}
