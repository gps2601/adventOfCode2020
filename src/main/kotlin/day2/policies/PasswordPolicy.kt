package day2.policies

import day2.PasswordEntry

interface PasswordPolicy {
    fun isValid(passwordEntry: PasswordEntry): Boolean
}