package daytwo.policies

class OldJobPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: String): Boolean {
        val (rangePattern, passwordPattern, password) = passwordEntry.split(" ")
        val range = rangePattern.split("-").map { it.toInt() }
        val occurrences = password.count { char -> char == passwordPattern.removeSuffix(":").single() }
        return occurrences in range.first()..range.last()
    }
}