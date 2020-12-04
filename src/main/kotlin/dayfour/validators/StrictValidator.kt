package dayfour.validators

import dayfour.Passport

class StrictValidator : PassportValidator {

    override fun isValid(passport: Passport): Boolean {
        val passportIdIsValid = passportIdIsValid(passport)
        return birthYearIsValid(passport) &&
                issueYearIsValid(passport) &&
                expiryYearIsValid(passport) &&
                heightIsValid(passport) &&
                hairColourIsValid(passport) &&
                eyeColourIsValid(passport) &&
                passportIdIsValid
    }

    private fun birthYearIsValid(passport: Passport) = passport.birthYear in 1920..2002

    private fun issueYearIsValid(passport: Passport) = passport.issueYear in 2010..2020

    private fun expiryYearIsValid(passport: Passport) = passport.expirationYear in 2020..2030

    private fun heightIsValid(passport: Passport): Boolean {
        val height = passport.height!!
        val range = if (height.endsWith("cm")) 150..193 else 59..76
        val value = height.substring(0, height.length - 2).toInt()
        return value in range
    }

    private fun hairColourIsValid(passport: Passport): Boolean {
        return passport.hairColour?.matches("^#[0-9a-f]{6}\$".toRegex()) == true
    }

    private fun eyeColourIsValid(passport: Passport): Boolean {
        val allowedHairColours = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        return allowedHairColours.contains(passport.eyeColour)
    }

    private fun passportIdIsValid(passport: Passport): Boolean {
        return passport.passportId?.length == 9
    }
}
