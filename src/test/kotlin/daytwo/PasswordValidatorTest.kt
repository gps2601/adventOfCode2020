package daytwo

import FileLoader
import daytwo.policies.OfficialTobogganPolicy
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordValidatorTest {
    val validator = PasswordValidator()
    val fileLoader = FileLoader()

    @Test
    internal fun `should return true for a valid password`() {
        assertTrue(validator.isValid("1-3 a: abcde"))
    }

    @Test
    internal fun `should return true for another valid password`() {
        assertTrue(validator.isValid("2-9 c: ccccccccc"))
    }

    @Test
    internal fun `should return false for an invalid password`() {
        assertFalse(validator.isValid("1-3 b: cdefg"))
    }

    @Test
    internal fun `part 1 solution`() {
        val passwords = fileLoader.createLinesFrom("daytwo/passwords.txt")

        val validPasswords = passwords.filter { validator.isValid(it) }.count()

        assertEquals(validPasswords, 569)
    }

    @Test
    internal fun `should return true if valid Official Toboggan password`() {
        assertTrue(validator.isValid("1-3 a: abcde", OfficialTobogganPolicy()))
    }

    @Test
    internal fun `should return false if invalid Official Toboggan password`() {
        assertFalse(validator.isValid("1-3 b: cdefg", OfficialTobogganPolicy()))
    }

    @Test
    internal fun `should return false if another invalid Official Toboggan password`() {
        assertFalse(validator.isValid("2-9 c: ccccccccc", OfficialTobogganPolicy()))
    }

    @Test
    internal fun `part two solution`() {
        val passwords = fileLoader.createLinesFrom("daytwo/passwords.txt")
        val validPasswords = passwords.filter { validator.isValid(it, OfficialTobogganPolicy()) }.count()

        assertEquals(validPasswords, 346)
    }
}