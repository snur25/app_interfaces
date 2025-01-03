package com.example.myapplication11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton

class RegisterFragment : Fragment() {

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
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialsManager = CredentialsManager()

        emailInput = view.findViewById(R.id.email_enter)
        passwordInput = view.findViewById(R.id.password_enter)
        emailEditText = view.findViewById(R.id.emailtext)
        passwordEditText = view.findViewById(R.id.passwordtext)
        nextButton = view.findViewById(R.id.myButton)
        termsCheckBox = view.findViewById(R.id.rectangle)
        memberLogin = view.findViewById(R.id.newmember)

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
                passwordInput.error = "You must accept the terms!"
            } else {
                passwordInput.error = null
            }

            if (isEmailValid && isPasswordValid && termsCheckBox.isChecked) {
                val result = credentialsManager.register(email, password)
                if (result.isSuccessful) {
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

                    // Switch back to LoginFragment
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment())
                        .commit()
                } else {
                    emailInput.error = result.errorMessage
                }
            }
        }

        memberLogin.setOnClickListener {
            // Switch to LoginFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }
}
