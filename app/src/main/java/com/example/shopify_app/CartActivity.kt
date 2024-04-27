package com.example.shopify_app

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class CartActivity : AppCompatActivity() {

    private lateinit var builder: Notification.Builder
    private lateinit var notificationChannel : NotificationChannel
    private lateinit var notificationManager : NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private val notificationId = 101

    lateinit var incr1 : TextView
    lateinit var incr2 : TextView
    lateinit var incr3 : TextView
    lateinit var decr1 : TextView
    lateinit var decr2 : TextView
    lateinit var decr3 : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val backBtn = findViewById<ImageView>(R.id.imageView34)
        backBtn.setOnClickListener {
            val j = Intent(this,DashboardScreenActivity::class.java)
            startActivity(j)
        }

        incr1 = findViewById(R.id.textView39)
        decr1 = findViewById(R.id.textView40)
        incr2 = findViewById(R.id.textView43)
        decr2 = findViewById(R.id.textView44)
        incr3 = findViewById(R.id.textView49)
        decr3 = findViewById(R.id.textView50)

        val q1 = findViewById<TextView>(R.id.textView41)
        val st1 = findViewById<TextView>(R.id.textView38)
        val p1 = findViewById<TextView>(R.id.textView37)

        val q3 = findViewById<TextView>(R.id.textView48)
        val st3 = findViewById<TextView>(R.id.textView51)
        val p3 = findViewById<TextView>(R.id.textView52)

        val q2 = findViewById<TextView>(R.id.textView42)
        val st2 = findViewById<TextView>(R.id.textView45)
        val p2 = findViewById<TextView>(R.id.textView46)

        val subTotal = findViewById<TextView>(R.id.textView56)
        val delivery = findViewById<TextView>(R.id.textView58)
        val taxes = findViewById<TextView>(R.id.textView59)
        val total = findViewById<TextView>(R.id.textView62)

        delivery.text = "20$"
        taxes.text = "10$"

        var res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
        subTotal.text = "$res$"
        var res2 = (20 + 10 + (res.toInt()))
        total.text = "$res2$"

        var q = (q1.text.toString().toInt())
        val p = (p1.text.toString().toInt())

        incr1.setOnClickListener {
            q += 1
            q1.text = (q).toString()
            st1.text = ((q) * (p)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

        decr1.setOnClickListener {
            q -= 1
            q1.text = (q).toString()
            st1.text = ((q) * (p)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

        var a = (q2.text.toString().toInt())
        val b = (p2.text.toString().toInt())

        incr2.setOnClickListener {
            a += 1
            q2.text = (a).toString()
            st2.text = ((a)*(b)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

        decr2.setOnClickListener {
            a -= 1
            q2.text = (a).toString()
            st2.text = ((a)*(b)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

        var c = (q3.text.toString().toInt())
        val d = (p3.text.toString().toInt())

        incr3.setOnClickListener {
            c += 1
            q3.text = (c).toString()
            st3.text = ((c)*(d)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

        decr3.setOnClickListener {
            c -= 1
            q3.text = (c).toString()
            st3.text = ((c)*(d)).toString()
            res = ((st1.text.toString().toInt()) + (st2.text.toString().toInt()) + (st3.text.toString().toInt())).toString()
            subTotal.text = "$res$"
            res2 = (20 + 10 + (res.toInt()))
            total.text = "$res2$"
        }

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