package com.example.shopify_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class DetailActivity3 : AppCompatActivity() {

    lateinit var back : ImageView
    lateinit var rateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail3)

        back = findViewById(R.id.imageView21)
        rateButton = findViewById(R.id.rateButton3)

        back.setOnClickListener {
            val i = Intent(this,WishlistActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        rateButton.setOnClickListener {
            Toast.makeText(this,"Thank you for the feedback!", Toast.LENGTH_SHORT).show()
        }
    }
}