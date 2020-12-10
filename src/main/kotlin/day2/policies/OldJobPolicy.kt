package day2.policies

import day2.PasswordEntry

class OldJobPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: PasswordEntry) = isCharacterCountInRange(passwordEntry)

    private fun isCharacterCountInRange(passwordEntry: PasswordEntry) =
        passwordEntry.password.count { char -> char == passwordEntry.pattern } in passwordEntry.range
}
