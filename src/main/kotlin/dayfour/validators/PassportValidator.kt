package dayfour.validators

import dayfour.Passport

interface PassportValidator {
    fun isValid(passport: Passport): Boolean
}
