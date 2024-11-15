package com.example.shopify_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Model.WishlistModel
import com.example.shopify_app.R

class WishlistAdapter(var c: Context, var l:MutableList<WishlistModel>,
                      var listener: RecyclerViewEvent
) :
    RecyclerView.Adapter<WishlistAdapter.MyHolder>() {

    inner class MyHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener
    {
        var nameText = v.findViewById<TextView>(R.id.textView26)
        var priceText = v.findViewById<TextView>(R.id.textView25)
        var ratingText = v.findViewById<TextView>(R.id.textView15)
        var reviewText = v.findViewById<TextView>(R.id.textView24)
        var img = v.findViewById<ImageView>(R.id.imageView25)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
            {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(c)
        val v: View = layoutInflater.inflate(R.layout.wishlist_item_custom_look,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return l.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.nameText.text = l[position].name
        holder.priceText.text = "Price: ${l[position].price}$"
        holder.ratingText.text = "${l[position].rating}"
        holder.reviewText.text = "${l[position].reviews}"
        holder.img.setImageResource(l[position].img)
    }

    interface RecyclerViewEvent{
        fun onItemClick(position: Int)
    }
}