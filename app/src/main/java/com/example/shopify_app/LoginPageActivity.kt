package com.example.shopify_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginPageActivity : AppCompatActivity() {

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null
    private var password: String? = null

    val SHARED_PREFS = "mySharedPref"
    val EMAIL_KEY = "email_key"
    val PASSWORD_KEY = "password_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val userId = findViewById<TextView>(R.id.username)
        val passKey = findViewById<TextView>(R.id.password)
        val fetchBtn = findViewById<Button>(R.id.fetchButton)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        username = sharedpreferences.getString(EMAIL_KEY, null)
        password = sharedpreferences.getString(PASSWORD_KEY,null)

        loginButton.setOnClickListener {

            if((userId.text.toString() != "") && (passKey.text.toString() != ""))
            {
                Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show()

                val editor = sharedpreferences.edit()
                editor.putString(EMAIL_KEY, userId.text.toString())
                editor.putString(PASSWORD_KEY, passKey.text.toString())
                editor.apply()

                val i = Intent(applicationContext,DashboardScreenActivity::class.java)
                startActivity(i)
                finish()
            }
            else
            {
                Toast.makeText(this,"Please enter valid Username and Password!",Toast.LENGTH_SHORT).show()
            }
        }

        fetchBtn.setOnClickListener {
            userId.text = sharedpreferences.getString(EMAIL_KEY,null)
            passKey.text = sharedpreferences.getString(PASSWORD_KEY,null)
        }
    }
}