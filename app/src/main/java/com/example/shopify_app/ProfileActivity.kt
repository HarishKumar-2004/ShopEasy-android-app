package com.example.shopify_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null

    val SHARED_PREFS = "mySharedPref"
    val EMAIL_KEY = "email_key"

    private lateinit var tv : TextView
    private lateinit var tv2 : TextView
    private lateinit var backButton : Button
    private lateinit var profilePicture : ImageView

    private lateinit var addressButton: Button

    private var selectedImageUri : Uri? = null
    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        username = sharedpreferences.getString(EMAIL_KEY,null)

        tv = findViewById(R.id.textView66)
        tv2 = findViewById(R.id.textView67)

        profilePicture = findViewById(R.id.imageView40)

        addressButton = findViewById(R.id.profileButton2)

        addressButton.setOnClickListener {
            val i = Intent(this,AddressActivity::class.java)
            startActivity(i)
        }

        profilePicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,PICK_IMAGE)
        }

        tv.text = username
        tv2.text = "$username@gmail.com"

        backButton = findViewById(R.id.profileButton6)

        backButton.setOnClickListener {
            val x = Intent(this,DashboardScreenActivity::class.java)
            startActivity(x)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==PICK_IMAGE && resultCode== Activity.RESULT_OK)
        {
            selectedImageUri = data?.data
            selectedImageUri?.let{
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,it)
                profilePicture.setImageBitmap(bitmap)
            }
        }
    }
}