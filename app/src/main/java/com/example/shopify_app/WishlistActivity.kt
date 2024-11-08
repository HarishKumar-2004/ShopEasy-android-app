package com.example.shopify_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class WishlistActivity : AppCompatActivity(), WishlistAdapter.RecyclerViewEvent {

    private lateinit var navBar : BottomNavigationView

    private lateinit var rv : RecyclerView
    private lateinit var adp : WishlistAdapter
    private var wishlistData : MutableList<WishlistModel> = mutableListOf(
        WishlistModel("Navy Blue Blazer",100,4.0f,5,R.drawable.blazer),
        WishlistModel("Shalimar Perfume",50,4.5f,7,R.drawable.perfume3),
        WishlistModel("Nike Blue Sneakers",140,3.8f,3,R.drawable.show))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        rv = findViewById(R.id.wishlistRecyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this,2)

        adp = WishlistAdapter(this,wishlistData,this@WishlistActivity)
        rv.adapter = adp

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

    override fun onItemClick(position: Int) {
        when(position)
        {
            0 -> startActivity(Intent(this@WishlistActivity,DetailActivity::class.java))
            1 -> startActivity(Intent(this@WishlistActivity,DetailActivity2::class.java))
            2 -> startActivity(Intent(this@WishlistActivity,DetailActivity3::class.java))
        }
    }
}