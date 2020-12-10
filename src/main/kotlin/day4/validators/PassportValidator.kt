package day4.validators

import day4.Passport

interface PassportValidator {
    fun isValid(passport: Passport): Boolean
}
