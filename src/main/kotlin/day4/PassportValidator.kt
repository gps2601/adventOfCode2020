package day4

import day4.PassportPolicy.LAPSE
import day4.PassportPolicy.STRICT
import day4.validators.LapseValidator
import day4.validators.StrictValidator

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
