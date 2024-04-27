package com.example.shopify_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SunglassesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sunglasses)

        val backButton = findViewById<ImageView>(R.id.imageView41)
        backButton.setOnClickListener {
            val i = Intent(this,DashboardScreenActivity::class.java)
            startActivity(i)
        }
    }
}