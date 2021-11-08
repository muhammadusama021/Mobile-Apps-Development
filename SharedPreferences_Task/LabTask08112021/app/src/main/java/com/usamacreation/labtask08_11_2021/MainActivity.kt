package com.usamacreation.labtask08_11_2021

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("temp", MODE_PRIVATE)
        val email = preferences.getString("email", null)

        val emailText: TextView =findViewById<TextView>(R.id.emailText)

        emailText.append(email)


    }
}