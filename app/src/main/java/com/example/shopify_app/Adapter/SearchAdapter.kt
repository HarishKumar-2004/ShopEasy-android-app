package com.example.shopify_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Model.Item
import com.example.shopify_app.R

class SearchAdapter(var c: Context, var l:MutableList<Item>, var listener:SearchListClickEvent ) :
    RecyclerView.Adapter<SearchAdapter.MyHolder>()
{
    // Define a filtered list for search
    private var filteredList = mutableListOf<Item>().apply { addAll(l) }

    // Add method to filter the list based on search query
    fun filterList(filteredItems: MutableList<Item>) {
        filteredList = filteredItems
        notifyDataSetChanged()
    }

    inner class MyHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var nameText = v.findViewById<TextView>(R.id.nameTextView)
        var brandText = v.findViewById<TextView>(R.id.brandTextView)
        var discountText = v.findViewById<TextView>(R.id.discountTextView)
        var priceText = v.findViewById<TextView>(R.id.priceTextView)
        var img = v.findViewById<ImageView>(R.id.itemImageView)

        init {
            v.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                {
                    listener.onClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(c)
        val v: View = layoutInflater.inflate(R.layout.dashboard_list_custom_look,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val item = filteredList[position]

        holder.nameText.text = item.name
        holder.discountText.text = "${item.discount}%"
        holder.priceText.text = "${item.price}$"
        holder.brandText.text = item.brand
        holder.img.setImageResource(item.img)
    }

    interface SearchListClickEvent {
        fun onClick(position: Int)
    }
}