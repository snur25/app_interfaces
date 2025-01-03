package com.example.myapplication11

class CredentialsManager {
    private val accounts = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
        return email.matches(emailRegex) && email.isNotEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 4
    }

    fun register(email: String, password: String): RegistrationResult {
        val normalizedEmail = email.lowercase()
        if (accounts.containsKey(normalizedEmail)) {
            return RegistrationResult(false, "Email already exists")
        }
        accounts[normalizedEmail] = password
        return RegistrationResult(true, null)
    }
}

data class RegistrationResult(val isSuccessful: Boolean, val errorMessage: String?)