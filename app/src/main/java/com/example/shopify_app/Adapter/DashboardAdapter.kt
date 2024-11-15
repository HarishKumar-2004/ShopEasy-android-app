package com.example.shopify_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Model.DashboardModel
import com.example.shopify_app.R

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

        val item = l[position]

        holder.nameText.text = item.name
        holder.discountText.text = "${item.discount} %"
        holder.priceText.text = "${item.price} $"
        holder.brandText.text = item.brand
        holder.img.setImageResource(item.img)
    }
    
}