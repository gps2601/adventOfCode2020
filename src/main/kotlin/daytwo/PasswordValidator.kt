package daytwo

import daytwo.policies.OldJobPolicy
import daytwo.policies.PasswordPolicy

class PasswordValidator {
    fun isValid(passwordEntry: String, policy: PasswordPolicy = OldJobPolicy()): Boolean {
        return policy.isValid(passwordEntry)
    }
}
