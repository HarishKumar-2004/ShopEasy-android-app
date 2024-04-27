package com.example.shopify_app

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.getSystemService
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardScreenActivity : AppCompatActivity() {

    private lateinit var builder: Notification.Builder
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var notificationManager : NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private val notificationId = 103

    lateinit var card1 : CardView
    lateinit var card2 : CardView
    lateinit var card3 : CardView
    lateinit var navBar : BottomNavigationView
    lateinit var textV : TextView

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null

    val SHARED_PREFS = "mySharedPref"
    val EMAIL_KEY = "email_key"
    val PASSWORD_KEY = "password_key"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_screen)

        sharedpreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE)
        username = sharedpreferences.getString(EMAIL_KEY,null)

        textV = findViewById(R.id.textView3)
        textV.text = username

        card1 = findViewById(R.id.card1)
        card2 = findViewById(R.id.card2)
        card3 = findViewById(R.id.card3)

        card1.setOnClickListener {

            val i = Intent(this,DetailActivity::class.java)
            startActivity(i)
        }

        card2.setOnClickListener {

            val i = Intent(this,DetailActivity2::class.java)
            startActivity(i)
        }

        card3.setOnClickListener {

            val i = Intent(this,DetailActivity3::class.java)
            startActivity(i)
        }

        navBar = findViewById(R.id.bottomNav)
        navBar.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.menu_wishlist -> {
                    val i = Intent(this,WishlistActivity::class.java)
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

        val shopNowButton = findViewById<Button>(R.id.button)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        shopNowButton.setOnClickListener {
            val intent = Intent(this,DashboardScreenActivity::class.java)
            pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)

            createNotificationChannel()
            notificationManager.notify(notificationId,builder.build())

            val y = Intent(this,SunglassesActivity::class.java)
            startActivity(y)
        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Notification title 2"
            val descriptionText = "Notification description 2"
            notificationChannel = NotificationChannel(name,descriptionText, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,name)
                .setSmallIcon(R.drawable.bell)
                .setContentTitle("Sale Notification")
                .setContentText("Shop latest sunglasses from top brands at Flat 30% discount!!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        else
        {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.bell)
                .setContentTitle("Sale Notification")
                .setContentText("Shop latest sunglasses from top brands at Flat 30% discount!!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
    }
}