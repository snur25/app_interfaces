package com.example.myapplication11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    private lateinit var emailInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var nextButton: Button
    private lateinit var termsCheckBox: CheckBox
    private lateinit var memberLogin: MaterialButton

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialsManager = CredentialsManager()

        emailInput = view.findViewById(R.id.valid_mail)
        passwordInput = view.findViewById(R.id.strong_password)
        emailEditText = view.findViewById(R.id.mailtext2)
        passwordEditText = view.findViewById(R.id.strongpasstext)
        nextButton = view.findViewById(R.id.myButton2)
        termsCheckBox = view.findViewById(R.id.rectangle2)
        memberLogin = view.findViewById(R.id.memberlogin)

        nextButton.setOnClickListener {
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
                    // Successfully logged in
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                    passwordInput.error = "Invalid credentials"
                }
            }
        }

        memberLogin.setOnClickListener {
            // Switch to RegisterFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .commit()
        }
    }
}
