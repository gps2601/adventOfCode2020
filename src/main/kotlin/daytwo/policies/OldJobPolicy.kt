package daytwo.policies

import daytwo.PasswordEntry

class OldJobPolicy : PasswordPolicy {
    override fun isValid(passwordEntry: PasswordEntry) = passwordEntry.password.count { char -> char == passwordEntry.pattern } in passwordEntry.range
}
