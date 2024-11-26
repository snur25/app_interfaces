package com.example.myapplication11

import org.junit.Test
import org.junit.Assert.assertEquals

class CredentialsManagerTest {

    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("wrongemailormat")
        assertEquals(false, isEmailValid)
    }

    // Test proper email
    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("test@te.st")
        assertEquals(true, isEmailValid)
    }

    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    // Test filled password
    @Test
    fun givenValidPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        val isPasswordValid = credentialsManager.isPasswordValid("1234")
        assertEquals(true, isPasswordValid)
    }

    @Test
    fun testValidEmail() {
        val manager = CredentialsManager()
        assertEquals(true, manager.isEmailValid("test@te.st"))
        assertEquals(false, manager.isEmailValid("invalid-email"))
    }

    @Test
    fun testValidPassword() {
        val manager = CredentialsManager()
        assertEquals(true, manager.isPasswordValid("1234"))
        assertEquals(false, manager.isPasswordValid("123"))
    }

    @Test
    fun testHardcodedCredentials() {
        val email = "test@te.st"
        val password = "1234"

        val manager = CredentialsManager()
        val isEmailValid = manager.isEmailValid(email)
        val isPasswordValid = manager.isPasswordValid(password)

        assertEquals(true, isPasswordValid && isEmailValid)
    }
}
