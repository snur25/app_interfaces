package com.example.myapplication11

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val emailInput: TextInputLayout
        get() = findViewById(R.id.email_enter)

    private val passwordInput: TextInputLayout
        get() = findViewById(R.id.password_enter)

    private val passwordEditText: TextInputEditText
        get() = findViewById(R.id.emailtext)

    private val emailEditText: TextInputEditText
        get() = findViewById(R.id.passwordtext)

    private val nextButton: Button
        get() = findViewById(R.id.myButton)

    private val termsCheckBox: CheckBox
        get() = findViewById(R.id.rectangle)

    private val memberLogin: ImageView
        get() = findViewById(R.id.newmember)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val credentialsManager = CredentialsManager()

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val isEmailValid = credentialsManager.isEmailValid(email)
            val isPasswordValid = credentialsManager.isPasswordValid(password)

            if(!isEmailValid){
                emailInput.error = "Invalid email format"
            } else {
                emailInput.error = null
            }

            if(!termsCheckBox.isChecked){
                passwordInput.error = "You must accept the terms!"
            }

            if(isEmailValid && isPasswordValid && termsCheckBox.isChecked){
                if (email == "test@te.st" && password == "1234"){
                    val intent = Intent(this, LoginActivityy::class.java)
                    startActivity(intent)
                } else{
                    passwordInput.error = "Invalid Credentials"
                }
            }
        }
        memberLogin.setOnClickListener {
            val intent = Intent(this, LoginActivityy::class.java)
            startActivity(intent)
        }

    }
}
