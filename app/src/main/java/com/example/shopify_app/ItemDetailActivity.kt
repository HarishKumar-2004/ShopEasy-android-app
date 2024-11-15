package com.example.shopify_app

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.shopify_app.Database.DatabaseHelper
import com.example.shopify_app.Model.Item
import com.example.shopify_app.Model.ItemDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var itemImage : ImageView
    private lateinit var itemName : TextView
    private lateinit var itemPrice : TextView
    private lateinit var itemDescription : TextView

    private lateinit var itemRating : RatingBar
    private lateinit var addToCartButton : Button
    private lateinit var rateButton : Button

    private val BASE_URL = "https://run.mocky.io/v3/"
    private val TAG : String = "CHECK_RESPONSE"

    private lateinit var db : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item_detail)

        backButton = findViewById(R.id.backArrowImage)
        itemImage = findViewById(R.id.itemImageView)
        itemName = findViewById(R.id.itemNameText)
        itemPrice = findViewById(R.id.itemPriceText)
        itemDescription = findViewById(R.id.itemDetailsText)

        itemRating = findViewById(R.id.itemRatingBar)
        rateButton = findViewById(R.id.rateButton)
        addToCartButton = findViewById(R.id.addToCartButton)

        backButton.setOnClickListener {
            val i = Intent(this,SearchActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        rateButton.setOnClickListener {
            Toast.makeText(this,"Thank you for the feedback!", Toast.LENGTH_SHORT).show()
        }

        val itemId = intent.getIntExtra("ITEM_ID",-1)
        val quantity = 1
        if(itemId != -1)
        {
            fetchItemDetails(itemId)
        }

        db = DatabaseHelper(this)

        addToCartButton.setOnClickListener {
            try
            {
                val name = itemName.text.toString()
                val price = itemPrice.text.toString().toInt()
                val desc = itemDescription.text.toString()
                val rating = itemRating.rating
                db.addToCart(itemId, name, price, quantity, price, desc, rating)
                Toast.makeText(this, "Item added to cart!", Toast.LENGTH_SHORT).show()
            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }
        }

    }

    private fun fetchItemDetails(itemId: Int) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getDataList().enqueue(object : Callback<MutableList<ItemDetail>>{
            override fun onResponse(
                call: Call<MutableList<ItemDetail>>,
                response: Response<MutableList<ItemDetail>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    val item = items?.find { it.id == itemId }
                    if (item != null) {
                        displayItemDetails(itemId, item)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<ItemDetail>>, t: Throwable) {
                Log.e(TAG,"onFailure: ${t.message}")
            }

        })
    }

    private fun displayItemDetails(itemId: Int, item: ItemDetail)
    {
        val img: Int = when(itemId){
            1 -> R.drawable.blazer
            2 -> R.drawable.top_wear
            3 -> R.drawable.perfume3
            4 -> R.drawable.watch3
            5 -> R.drawable.item_1
            6 -> R.drawable.show
            7 -> R.drawable.pants
            8 -> R.drawable.perfume2
            9 -> R.drawable.shoes2
            10 -> R.drawable.sunglass1
            11 -> R.drawable.perfume
            12 -> R.drawable.watch2
            13 -> R.drawable.sunglass2
            14 -> R.drawable.sunglass3
            15 -> R.drawable.shoes_cat
            16 -> R.drawable.sunglass4
            17 -> R.drawable.jeans
            else -> return
        }

        itemName.text = item.name
        itemPrice.text = "${item.price}"
        itemDescription.text = item.description
        itemRating.rating = item.rating
        itemImage.setImageResource(img)
    }

}