package com.example.myapplication11

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivityy : AppCompatActivity() {
    private val emailInput: TextInputLayout
        get() = findViewById(R.id.valid_mail)

    private val passwordInput: TextInputLayout
        get() = findViewById(R.id.strong_password)

    private val passwordEditText: TextInputEditText
        get() = findViewById(R.id.mailtext2)

    private val emailEditText: TextInputEditText
        get() = findViewById(R.id.strongpasstext)

    private val nextButton: Button
        get() = findViewById(R.id.myButton2)

    private val termsCheckBox: CheckBox
        get() = findViewById(R.id.rectangle2)

    private val memberLogin: ImageView
        get() = findViewById(R.id.memberlogin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val credentialsManager = CredentialsManager()

        nextButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val isEmailValid = credentialsManager.isEmailValid(email)
            val isPasswordValid = credentialsManager.isPasswordValid(password)

            if (!isEmailValid) {
                emailInput.error = "Invalid email format"
            } else {
                emailInput.error = null
            }

            if (!termsCheckBox.isChecked) {
                passwordInput.error = "You must accept the terms"
            }

            if (isEmailValid && isPasswordValid && termsCheckBox.isChecked) {
                if (email == "test@te.st" && password == "1234") {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    passwordInput.error = "Invalid credentials"
                }
            }
        }
        memberLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}