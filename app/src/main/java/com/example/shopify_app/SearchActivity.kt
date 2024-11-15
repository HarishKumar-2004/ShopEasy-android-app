package com.example.shopify_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Adapter.SearchAdapter
import com.example.shopify_app.Model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class SearchActivity : AppCompatActivity(), SearchAdapter.SearchListClickEvent {

    private lateinit var searchView: SearchView
    private lateinit var rv : RecyclerView

    private var dataList : MutableList<Item> = mutableListOf()
    private lateinit var adp : SearchAdapter

    private lateinit var backArrow : ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search2)

        searchView = findViewById(R.id.searchView)

        backArrow = findViewById(R.id.backArrowImage2)
        backArrow.setOnClickListener {
            startActivity(Intent(this@SearchActivity,DashboardScreenActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        rv = findViewById(R.id.searchRecyclerView)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

//        getData()
        dataList.add(Item(1,"Navy Blue Blazer","Allen Solly",15,100,R.drawable.blazer))
        dataList.add(Item(2,"Red Shacket","H&M",40,55,R.drawable.top_wear))
        dataList.add(Item(3,"Shalimar Perfume","Shalimar",10,50,R.drawable.perfume3))
        dataList.add(Item(4,"Wrist Watch","Breitling",10,200,R.drawable.watch3))
        dataList.add(Item(5,"Plain Black T-shirt","Allen solly",40,15,R.drawable.item_1))
        dataList.add(Item(6,"Blue Sneakers","Nike",25,140,R.drawable.show))
        dataList.add(Item(7,"Off-white Chinos","Van Heusen",20,35,R.drawable.pants))
        dataList.add(Item(8,"Summer Fragrance","Louis Vuitton",10,170,R.drawable.perfume2))
        dataList.add(Item(9,"Brown oxfords","Louis Phillipe",25,80,R.drawable.shoes2))
        dataList.add(Item(10,"Black Sunglasses","Ray Ban",5,70,R.drawable.sunglass1))
        dataList.add(Item(11,"Upsilon fragrance","Upsilon",5,65,R.drawable.perfume))
        dataList.add(Item(12,"Dress watch","Omega",5,150,R.drawable.watch2))
        dataList.add(Item(13,"Cool Sunglasses","Fast-track",30,30,R.drawable.sunglass2))
        dataList.add(Item(14,"Red Sunglasses","Oakley",20,90,R.drawable.sunglass3))
        dataList.add(Item(15,"Black Shoes","Red Tape",10,90,R.drawable.shoes_cat))
        dataList.add(Item(16,"Blue Sunglasses","Lenskart",40,55,R.drawable.sunglass4))
        dataList.add(Item(17,"Blue Jeans","Lee Cooper",20,35,R.drawable.jeans))



        adp = SearchAdapter(this,dataList,this@SearchActivity)
        rv.adapter = adp

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?) {
        val searchList : MutableList<Item> = mutableListOf()
        if (!query.isNullOrEmpty()) {
            for (it in dataList) {
                if (it.name.uppercase(Locale.getDefault())
                        .contains(query.uppercase(Locale.getDefault())))
                {
                    searchList.add(it)
                }
            }
        } else {
            searchList.addAll(dataList)
        }
        adp.filterList(searchList)
    }

    override fun onClick(position: Int) {

        val selectedItem = dataList[position]

        val intent = Intent(this@SearchActivity,ItemDetailActivity::class.java)
        intent.putExtra("ITEM_ID",selectedItem.id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}