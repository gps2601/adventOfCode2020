package daytwo

import daytwo.policies.PasswordPolicy

class PasswordValidator(private val policy: PasswordPolicy) {
    fun validate(passwordEntryInput: String) = policy.isValid(PasswordEntry(passwordEntryInput))
}
