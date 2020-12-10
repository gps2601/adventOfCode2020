package day2

import day2.policies.PasswordPolicy

class PasswordValidator(private val policy: PasswordPolicy) {
    fun validate(passwordEntryInput: String) = policy.isValid(PasswordEntry(passwordEntryInput))
}
