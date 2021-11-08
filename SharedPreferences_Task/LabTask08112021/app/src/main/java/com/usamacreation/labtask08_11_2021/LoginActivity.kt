package com.usamacreation.labtask08_11_2021

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn:Button=findViewById<Button>(R.id.submitBtn)
        val email:TextView=findViewById<TextView>(R.id.emailBox)

        val preferences = getSharedPreferences("temp", MODE_PRIVATE)
        val name = preferences.getString("email", null)
        if (name != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        email.setText(name)
        btn.setOnClickListener()
        {
            savePref()
            }
    }

    private fun savePref() {
        val btn:Button=findViewById<Button>(R.id.submitBtn)
        val email:TextView=findViewById<TextView>(R.id.emailBox)
        val password:TextView=findViewById<TextView>(R.id.passwordBox)
        if(email.text.toString() != "" && password.text.toString() != "")
        {
            var emailValue=email.text.toString()
            var pass=password.text.toString()
            val preferences = getSharedPreferences("temp", MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("email", emailValue)
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }



    }
}