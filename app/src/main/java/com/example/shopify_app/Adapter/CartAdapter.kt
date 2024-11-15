package com.example.shopify_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.shopify_app.Database.DatabaseHelper
import com.example.shopify_app.Model.CartItem
import com.example.shopify_app.Model.Item
import com.example.shopify_app.Model.ItemDetail
import com.example.shopify_app.R

class CartAdapter(var c: Context, var l:MutableList<ItemDetail>) :
    RecyclerView.Adapter<CartAdapter.MyHolder>(){

        val db = DatabaseHelper(c)

    inner class MyHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var nameText = v.findViewById<TextView>(R.id.cartItemNameText)
        var itemPriceText = v.findViewById<TextView>(R.id.cartItemPriceText)
        var totalPriceText = v.findViewById<TextView>(R.id.cartItemTotalPrice)
        var quantityText = v.findViewById<TextView>(R.id.cartItemQuantityText)
        var img = v.findViewById<ImageView>(R.id.cartItemImageView)
        var add = v.findViewById<TextView>(R.id.addText)
        var subtract = v.findViewById<TextView>(R.id.minusText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(c)
        val v: View = layoutInflater.inflate(R.layout.custom_cart_item,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return l.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val item = l[position]

        val itemId = item.id
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
            else -> -1
        }

        holder.nameText.text = item.name
        holder.itemPriceText.text = "${item.price}$"
        holder.totalPriceText.text = "${item.totalPrice}$"
        holder.quantityText.text = "${item.quantity}"
        holder.img.setImageResource(img)

        var q = item.quantity
        val ip = item.price
        var tp: Int

        holder.add.setOnClickListener{
            q += 1
            tp = q*(ip)
            item.quantity = q
            item.totalPrice = tp
            holder.totalPriceText.text = "${tp}$"
            holder.quantityText.text = "${q}"

            notifyDataSetChanged()
        }

        holder.subtract.setOnClickListener{
            if(q==1)
            {
                l.removeAt(position)
                db.deleteItemFromCart(item.id)
                Toast.makeText(c,"Removed item from cart!",Toast.LENGTH_LONG).show()
            }
            else
            {
                q -= 1
                tp = q*(ip)
                item.quantity = q
                item.totalPrice = tp
                holder.totalPriceText.text = "${tp}$"
                holder.quantityText.text = "${q}"
            }

            notifyDataSetChanged()
        }
    }
}