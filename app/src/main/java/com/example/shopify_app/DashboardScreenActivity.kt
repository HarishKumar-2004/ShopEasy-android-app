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
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DashboardScreenActivity : AppCompatActivity() {

    private lateinit var builder: Notification.Builder
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var notificationManager : NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private val notificationId = 103

    private lateinit var fabMenu : FloatingActionButton
    private lateinit var fabWishlist : FloatingActionButton
    private lateinit var fabCart : FloatingActionButton
    private lateinit var fabProfile : FloatingActionButton
    lateinit var textV : TextView

    private lateinit var rv : RecyclerView
    private lateinit var adp : DashboardAdapter
    private lateinit var list :MutableList<DashboardModel>

    private lateinit var sharedpreferences: SharedPreferences
    private var username: String? = null

    val SHARED_PREFS = "mySharedPref"
    val EMAIL_KEY = "email_key"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_screen)

        sharedpreferences = getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE)
        username = sharedpreferences.getString(EMAIL_KEY,null)

        textV = findViewById(R.id.textView3)
        textV.text = username

        rv = findViewById(R.id.recyclerView)
        rv.setHasFixedSize(true)

        val gm= LinearLayoutManager(this)
        gm.orientation=RecyclerView.VERTICAL
        rv.layoutManager=gm

        list = mutableListOf(
            DashboardModel("Plain Black Tshirt","Allen Solly",40,15,R.drawable.item_1),
            DashboardModel("Perfume","Shalimar",10,50,R.drawable.perfume3),
            DashboardModel("Sneakers","Nike",25,140,R.drawable.show),
            DashboardModel("Black Sun-glass","Ray Ban",15,70,R.drawable.sunglass1),
            DashboardModel("Dress Watch","Omega",5,150,R.drawable.watch2),
            DashboardModel("Blue Jeans","Lee Cooper",20,35,R.drawable.jeans))

        adp = DashboardAdapter(this,list)
        rv.adapter = adp


        fabMenu = findViewById(R.id.fabMenu)
        fabWishlist = findViewById(R.id.fabWishList)
        fabProfile = findViewById(R.id.fabProfile)
        fabCart = findViewById(R.id.fabCart)

        var fabVisible = true

        fabWishlist.setOnClickListener {
            val intent = Intent(this,WishlistActivity::class.java)
            startActivity(intent)
        }

        fabCart.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }

        fabProfile.setOnClickListener {
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        }

        fabMenu.setOnClickListener {
            if(fabVisible==false)
            {
                fabWishlist.show()
                fabCart.show()
                fabProfile.show()
                fabWishlist.visibility = View.VISIBLE
                fabProfile.visibility = View.VISIBLE
                fabCart.visibility = View.VISIBLE
                fabMenu.setImageDrawable(resources.getDrawable(R.drawable.baseline_cancel_24))
                fabVisible = true


            }
            else
            {
                fabWishlist.hide()
                fabCart.hide()
                fabProfile.hide()
                fabWishlist.visibility = View.GONE
                fabProfile.visibility = View.GONE
                fabCart.visibility = View.GONE
                fabMenu.setImageDrawable(resources.getDrawable(R.drawable.baseline_menu_24))
                fabVisible = false

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