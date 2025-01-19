package com.example.myapplication11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    private lateinit var emailInput: TextInputLayout
    private lateinit var passwordInput: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var nextButton: Button
    private lateinit var memberLogin: MaterialButton

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        memberLogin = view.findViewById(R.id.memberlogin)

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            val loginResult = credentialsManager.login(email, password)

            if (!credentialsManager.isEmailValid(email)) {
                emailInput.error = "Invalid email format"
            } else {
                emailInput.error = null
            }

            if (!credentialsManager.isPasswordValid(password)) {
                passwordInput.error = "Password must be at least 4 characters"
            } else {
                passwordInput.error = null
            }

            if (loginResult.isSuccessful) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to RecipesFragment
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RecipesFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(context, loginResult.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        memberLogin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
