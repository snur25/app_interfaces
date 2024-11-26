package com.example.myapplication11

class CredentialsManager {

        fun isEmailValid(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()
            return email.matches(emailRegex) && email.isNotEmpty()
        }

        fun isPasswordValid(password: String): Boolean {
            return password.length >= 4
        }
}