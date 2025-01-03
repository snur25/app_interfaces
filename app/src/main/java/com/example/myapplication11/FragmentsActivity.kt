package com.example.myapplication11

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentsActivity : AppCompatActivity() {
    private var isFragmentAVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        // Load the initial fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FragmentA())
            .commit()

        val switchButton: Button = findViewById(R.id.switchButton)

        // Switch fragments on button click
        switchButton.setOnClickListener {
            val fragment: Fragment = if (isFragmentAVisible) {
                FragmentB()
            } else {
                FragmentA()
            }
            isFragmentAVisible = !isFragmentAVisible

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }
}
