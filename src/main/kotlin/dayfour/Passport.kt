package dayfour

class Passport(passportEntry: String) {
    var birthYear: Int? = null
    var issueYear: Int? = null
    var expirationYear: Int? = null
    var height: String? = null
    var hairColour: String? = null
    var eyeColour: String? = null
    var passportId: String? = null
    var countryId: String? = null

    init {
        val rawPassportFields = passportEntry.split(" ", "\n").map { it.split(":") }.map { it.first() to it.last() }
        birthYear = getValueForProperty(rawPassportFields, "byr")?.toInt()
        issueYear = getValueForProperty(rawPassportFields, "iyr")?.toInt()
        expirationYear = getValueForProperty(rawPassportFields, "eyr")?.toInt()
        height = getValueForProperty(rawPassportFields, "hgt")
        hairColour = getValueForProperty(rawPassportFields, "hcl")
        eyeColour = getValueForProperty(rawPassportFields, "ecl")
        passportId = getValueForProperty(rawPassportFields, "pid")
        countryId = getValueForProperty(rawPassportFields, "cid")
    }

    private fun getValueForProperty(rawPassportFields: List<Pair<String, String>>, targetProperty: String) =
        rawPassportFields.getOrNull(rawPassportFields.indexOfFirst { it.first == targetProperty })?.second
}
