package dayfour

import FileLoader
import dayfour.PassportPolicy.LAPSE
import dayfour.PassportPolicy.STRICT
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class PassportValidatorTest {
    private val fileLoader = FileLoader()
    private var passportEntries: List<String> = fileLoader.createLinesFromDoubleLineBreaked("dayfour/passports.txt")
    private var passportValidator = PassportValidator()

    @Test
    internal fun `part 1 solution`() {
        val validPassports = passportEntries.filter {
            passportValidator.isValid(it, LAPSE)
        }.count()

        assertThat(validPassports, equalTo(250))
    }

    @Test
    internal fun `part 2 solution`() {
        val validPassports = passportEntries.filter {
            passportValidator.isValid(it, STRICT)
        }.count()

        assertThat(validPassports, equalTo(158))
    }
}
