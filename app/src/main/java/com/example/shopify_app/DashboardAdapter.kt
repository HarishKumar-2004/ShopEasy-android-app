package com.example.shopify_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DashboardAdapter(var c: Context, var l:MutableList<DashboardModel>) :
    RecyclerView.Adapter<DashboardAdapter.MyHolder>()
{
    inner class MyHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var nameText = v.findViewById<TextView>(R.id.nameTextView)
        var brandText = v.findViewById<TextView>(R.id.brandTextView)
        var discountText = v.findViewById<TextView>(R.id.discountTextView)
        var priceText = v.findViewById<TextView>(R.id.priceTextView)
        var img = v.findViewById<ImageView>(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(c)
        val v: View = layoutInflater.inflate(R.layout.dashboard_list_custom_look,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return l.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.nameText.text = l[position].name
        holder.discountText.text = "${l[position].discount} %"
        holder.priceText.text = "${l[position].price} $"
        holder.brandText.text = l[position].brand
        holder.img.setImageResource(l[position].img)
    }
    
}