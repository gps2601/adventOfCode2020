package daytwo.policies

class OldJobPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: String): Boolean {
        val entrySections = passwordEntry.split(" ")
        val numberOfTimes = entrySections.first()
        val min = numberOfTimes.split("-").first().toInt()
        val max = numberOfTimes.split("-").last().toInt()
        val pattern = entrySections[1].toCharArray().first()
        val password = entrySections.last()

        val occurrences = password.count{ char-> char == pattern}
        return occurrences in min..max
    }
}