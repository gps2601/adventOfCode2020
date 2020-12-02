package daytwo.policies

interface PasswordPolicy {
    fun isValid(passwordEntry: String): Boolean
}