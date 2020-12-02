package daytwo.policies

import daytwo.PasswordEntry

interface PasswordPolicy {
    fun isValid(passwordEntry: PasswordEntry): Boolean
}