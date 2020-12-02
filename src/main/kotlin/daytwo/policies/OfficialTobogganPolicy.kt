package daytwo.policies

class OfficialTobogganPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: String): Boolean {
        val (positions, pattern, password) = passwordEntry.split(" ")
        val (firstPosition, secondPosition) = positions.split("-").map { it.toInt() - 1 }
        val targetChar = pattern.first()
        return matchInExactlyOnePosition(password, targetChar, firstPosition, secondPosition)
    }

    private fun matchInExactlyOnePosition(password: String, targetChar: Char, firstPosition: Int, secondPosition: Int): Boolean {
        return (password[firstPosition] == targetChar).xor(password[secondPosition] == targetChar)
    }
}
