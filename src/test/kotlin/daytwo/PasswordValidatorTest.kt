package daytwo

import FileLoader
import daytwo.policies.OfficialTobogganPolicy
import daytwo.policies.OldJobPolicy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordValidatorTest {
    private val fileLoader = FileLoader()

    @Test
    internal fun `should return true for a valid password`() {
        val validator = PasswordValidator(OldJobPolicy())

        assertTrue(validator.validate("1-3 a: abcde"))
    }

    @Test
    internal fun `should return true for another valid password`() {
        val validator = PasswordValidator(OldJobPolicy())

        assertTrue(validator.validate("2-9 c: ccccccccc"))
    }

    @Test
    internal fun `should return false for an invalid password`() {
        val validator = PasswordValidator(OldJobPolicy())

        assertFalse(validator.validate("1-3 b: cdefg"))
    }

    @Test
    internal fun `part 1 solution`() {
        val validator = PasswordValidator(OldJobPolicy())
        val passwords = fileLoader.createLinesFrom("daytwo/passwords.txt")

        val validPasswords = passwords.filter { validator.validate(it) }.count()

        assertEquals(validPasswords, 569)
    }

    @Test
    internal fun `should return true if valid Official Toboggan password`() {
        val validator = PasswordValidator(OfficialTobogganPolicy())

        assertTrue(validator.validate("1-3 a: abcde"))
    }

    @Test
    internal fun `should return false if invalid Official Toboggan password`() {
        val validator = PasswordValidator(OfficialTobogganPolicy())

        assertFalse(validator.validate("1-3 b: cdefg"))
    }

    @Test
    internal fun `should return false if another invalid Official Toboggan password`() {
        val validator = PasswordValidator(OfficialTobogganPolicy())

        assertFalse(validator.validate("2-9 c: ccccccccc"))
    }

    @Test
    internal fun `part two solution`() {
        val validator = PasswordValidator(OfficialTobogganPolicy())
        val passwords = fileLoader.createLinesFrom("daytwo/passwords.txt")
        val validPasswords = passwords.filter { validator.validate(it) }.count()

        assertEquals(validPasswords, 346)
    }
}