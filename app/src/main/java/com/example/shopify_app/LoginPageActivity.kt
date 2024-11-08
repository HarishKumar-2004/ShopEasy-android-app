package com.example.shopify_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginPageActivity : AppCompatActivity() {

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null
    private var password: String? = null

    private lateinit var auth: FirebaseAuth

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
        val signUpTextView = findViewById<TextView>(R.id.textView87)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        username = sharedpreferences.getString(EMAIL_KEY, null)
        password = sharedpreferences.getString(PASSWORD_KEY,null)

        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {

            val uname  = userId.text.toString()
            val pwd = passKey.text.toString()

            if((uname != "") && (pwd != ""))
            {
                val editor = sharedpreferences.edit()
                editor.putString(EMAIL_KEY, userId.text.toString())
                editor.putString(PASSWORD_KEY, passKey.text.toString())
                editor.apply()
            }
            login(uname,pwd)
        }

        signUpTextView.setOnClickListener {
            val i = Intent(this,SignUpActivity::class.java)
            startActivity(i)
        }

        fetchBtn.setOnClickListener {
            userId.text = sharedpreferences.getString(EMAIL_KEY,null)
            passKey.text = sharedpreferences.getString(PASSWORD_KEY,null)
        }
    }

    private fun login(email1:String, pwd1:String)
    {
        auth.signInWithEmailAndPassword(email1, pwd1).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent = Intent(this,DashboardScreenActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Login Successful!",Toast.LENGTH_LONG).show()
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}