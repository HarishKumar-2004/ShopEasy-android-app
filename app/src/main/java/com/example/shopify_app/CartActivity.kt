package com.example.shopify_app

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Adapter.CartAdapter
import com.example.shopify_app.Database.DatabaseHelper
import com.example.shopify_app.Model.CartItem
import com.example.shopify_app.Model.ItemDetail


class CartActivity : AppCompatActivity() {

    private lateinit var builder: Notification.Builder
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var notificationManager : NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private val notificationId = 101


    private lateinit var addressArrow : ImageView

    private lateinit var rv : RecyclerView
    private lateinit var adp : CartAdapter

    private lateinit var db : DatabaseHelper

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        addressArrow = findViewById(R.id.imageView36)
        addressArrow.setOnClickListener {
            val intent = Intent(this@CartActivity,AddressActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val backBtn = findViewById<ImageView>(R.id.imageView34)
        backBtn.setOnClickListener {
            val j = Intent(this,DashboardScreenActivity::class.java)
            startActivity(j)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        rv = findViewById(R.id.cartRecyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        db = DatabaseHelper(this)
        var cartItems = mutableListOf<ItemDetail>()
        cartItems = db.getAllItemDetails().toMutableList()

        try {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty!!", Toast.LENGTH_SHORT).show()
            }

            adp = CartAdapter(this@CartActivity,cartItems)
            rv.adapter = adp
        }
        catch (e : Exception)
        {
            Log.e("CartActivity", "Error in CartActivity: ${e.message}")
            e.printStackTrace()
            Toast.makeText(this, "Failed to load cart items!", Toast.LENGTH_SHORT).show()
        }

//        cartList.add(CartItem("Nike Sneakers",140,140,1,R.drawable.show))
//        cartList.add(CartItem("Red Shacket",55,55,1,R.drawable.top_wear))
//        cartList.add(CartItem("Wrist watch",200,200,1,R.drawable.watch3))

        val subTotal = findViewById<TextView>(R.id.textView56)
        val delivery = findViewById<TextView>(R.id.textView58)
        val taxes = findViewById<TextView>(R.id.textView59)
        val total = findViewById<TextView>(R.id.textView62)

        delivery.text = "20$"
        taxes.text = "10$"

        var res : Int = 0
        for(data in cartItems)
        {
            res = res + data.totalPrice
        }
        subTotal.text = "$res$"
        val res2 = (20 + 10 + (res.toInt()))
        total.text = "$res2$"



        val order = findViewById<Button>(R.id.orderButton)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        order.setOnClickListener {
            val intent = Intent(this,CartActivity::class.java)
            pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)

            createNotificationChannel()
            notificationManager.notify(notificationId,builder.build())
        }

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Notification title"
            val descriptionText = "Notification description"
            notificationChannel = NotificationChannel(name,descriptionText,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,name)
                .setSmallIcon(R.drawable.bell)
                .setContentTitle("Order Confirmation")
                .setContentText("Your order has been successfully placed. Happy Trials!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }

        else
        {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.bell)
                .setContentTitle("Order Confirmation")
                .setContentText("Your order has been successfully placed. Happy Trials!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
    }

}