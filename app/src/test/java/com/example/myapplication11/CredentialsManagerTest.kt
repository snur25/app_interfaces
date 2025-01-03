package com.example.myapplication11

import org.junit.Test
import org.junit.Assert.assertEquals

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("wrongemailformat")
        assertEquals(false, isEmailValid)
    }

    // Test proper email
    @Test
    fun givenProperEmail_thenReturnTrue() {
        val isEmailValid = credentialsManager.isEmailValid("test@te.st")
        assertEquals(true, isEmailValid)
    }

    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    // Test valid password
    @Test
    fun givenValidPassword_thenReturnTrue() {
        val isPasswordValid = credentialsManager.isPasswordValid("1234")
        assertEquals(true, isPasswordValid)
    }

    // Test successful registration
    @Test
    fun givenNewEmailAndPassword_thenRegisterSuccessfully() {
        val result = credentialsManager.register("test@te.st", "password")
        assertEquals(true, result.isSuccessful)
        assertEquals(null, result.errorMessage)
    }

    // Test duplicate email registration
    @Test
    fun givenDuplicateEmail_thenReturnError() {
        credentialsManager.register("test@te.st", "password")
        val result = credentialsManager.register("test@te.st", "newpassword")
        assertEquals(false, result.isSuccessful)
        assertEquals("Email already exists", result.errorMessage)
    }

    // Test case-insensitive email registration
    @Test
    fun givenDuplicateEmailInDifferentCase_thenReturnError() {
        credentialsManager.register("TEST@te.st", "password")
        val result = credentialsManager.register("test@te.st", "newpassword")
        assertEquals(false, result.isSuccessful)
        assertEquals("Email already exists", result.errorMessage)
    }

    // Test valid credentials after registration
    @Test
    fun givenValidEmailAndPasswordAfterRegistration_thenCredentialsAreValid() {
        credentialsManager.register("test@te.st", "password")
        val isEmailValid = credentialsManager.isEmailValid("test@te.st")
        val isPasswordValid = credentialsManager.isPasswordValid("password")
        assertEquals(true, isEmailValid && isPasswordValid)
    }
}
