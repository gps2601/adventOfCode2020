package daytwo.policies

import daytwo.PasswordEntry

class OfficialTobogganPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: PasswordEntry) = matchInExactlyOnePosition(passwordEntry)

    private fun matchInExactlyOnePosition(passwordEntry: PasswordEntry) =
        positionMatches(passwordEntry, passwordEntry.firstPosition) xor positionMatches(passwordEntry, passwordEntry.secondPosition)

    private fun positionMatches(passwordEntry: PasswordEntry, position: Int) = passwordEntry.password[position] == passwordEntry.pattern
}
