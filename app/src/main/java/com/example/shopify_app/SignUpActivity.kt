package com.example.shopify_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        val usernameEditText = findViewById<EditText>(R.id.usernameET)
        val passwordEditText = findViewById<EditText>(R.id.passwordET)
        val signupButton = findViewById<Button>(R.id.signupButton)

        signupButton.setOnClickListener {
            val email = usernameEditText.text.toString()
            val pwd = passwordEditText.text.toString()
            register(email,pwd)
        }
    }

    private fun register(email1:String, pwd1:String)
    {

        auth.createUserWithEmailAndPassword(email1,pwd1).addOnCompleteListener { task ->
            if(task.isSuccessful)
            {
                val i = Intent(this,DashboardScreenActivity::class.java)
                startActivity(i)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}