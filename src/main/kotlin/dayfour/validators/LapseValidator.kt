package dayfour.validators

import dayfour.Passport

class LapseValidator : PassportValidator {

    override fun isValid(passport: Passport): Boolean {
        return hasRequiredFields(passport)
    }

    private fun hasRequiredFields(passport: Passport) =
        passport.birthYear != null &&
                passport.issueYear != null &&
                passport.expirationYear != null &&
                passport.height != null &&
                passport.hairColour != null &&
                passport.eyeColour != null &&
                passport.passportId != null
}
