package com.example.shopify_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class WishlistActivity : AppCompatActivity() {

    private lateinit var navBar : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        navBar = findViewById(R.id.bottomNav2)
        navBar.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.menu_home -> {
                    val i = Intent(this,DashboardScreenActivity::class.java)
                    startActivity(i)
                    true
                }
                R.id.menu_cart -> {
                    val j = Intent(this,CartActivity::class.java)
                    startActivity(j)
                    true
                }
                R.id.menu_profile -> {
                    val k = Intent(this,ProfileActivity::class.java)
                    startActivity(k)
                    true
                }

                else -> {true}
            }
        }
    }
}