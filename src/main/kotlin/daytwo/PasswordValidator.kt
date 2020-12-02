package daytwo

import daytwo.policies.PasswordPolicy

class PasswordValidator(private val policy: PasswordPolicy) {
    fun validate(passwordEntry: String) = policy.isValid(passwordEntry)
}
