package daytwo.policies

class OfficialTobogganPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: String): Boolean {
        val passwordEntrySections = passwordEntry.split(" ")
        val positions = passwordEntrySections.first().split("-")
        val firstPosition = positions.first().toInt() - 1
        val secondPosition = positions.last().toInt() - 1
        val targetChar = passwordEntrySections[1].first()
        val password = passwordEntrySections.last()
        return (password[firstPosition] == targetChar).xor(password[secondPosition] == targetChar)
    }
}