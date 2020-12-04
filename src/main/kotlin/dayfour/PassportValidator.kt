package dayfour

import dayfour.PassportPolicy.LAPSE
import dayfour.PassportPolicy.STRICT
import dayfour.validators.LapseValidator
import dayfour.validators.StrictValidator

class PassportValidator {
    private val lapseValidator = LapseValidator()
    private val strictValidator = StrictValidator()

    fun isValid(passportEntry: String, policy: PassportPolicy): Boolean {
        val passport = Passport(passportEntry)
        return when(policy) {
            LAPSE -> lapseValidator.isValid(passport)
            STRICT -> lapseValidator.isValid(passport) && strictValidator.isValid(passport)
        }
    }
}

enum class PassportPolicy {
    LAPSE, STRICT
}
