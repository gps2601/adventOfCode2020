package daytwo.policies

import daytwo.PasswordEntry

class OfficialTobogganPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: PasswordEntry) = matchInExactlyOnePosition(passwordEntry)

    private fun matchInExactlyOnePosition(passwordEntry: PasswordEntry) =
        (passwordEntry.password[passwordEntry.firstPosition] == passwordEntry.pattern)
            .xor(passwordEntry.password[passwordEntry.secondPosition] == passwordEntry.pattern)
}
