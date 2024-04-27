package com.example.shopify_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class DetailActivity2 : AppCompatActivity() {

    lateinit var back : ImageView
    lateinit var rateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        back = findViewById(R.id.imageView17)
        rateButton = findViewById(R.id.rateButton2)

        back.setOnClickListener {
            val i = Intent(this,DashboardScreenActivity::class.java)
            startActivity(i)
        }

        rateButton.setOnClickListener {
            Toast.makeText(this,"Thank you for the feedback!", Toast.LENGTH_SHORT).show()
        }
    }
}