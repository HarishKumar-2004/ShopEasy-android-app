package com.example.shopify_app.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shopify_app.Model.ItemDetail

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "ItemDatabase"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Users"
        const val COLUMN_ID = "Id"
        const val COLUMN_ITEM_ID = "ItemId"
        const val COLUMN_NAME = "Name"
        const val COLUMN_ITEM_PRICE = "Price"
        const val COLUMN_TOTAL_PRICE = "TotalPrice"
        const val COLUMN_QUANTITY = "Quantity"
        const val COLUMN_DESCRIPTION = "Description"
        const val COLUMN_RATING = "Rating"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_ITEM_ID INTEGER, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_ITEM_PRICE INTEGER, "
                + "$COLUMN_QUANTITY INTEGER, "
                + "$COLUMN_TOTAL_PRICE INTEGER, "
                + "$COLUMN_DESCRIPTION TEXT, "
                + "$COLUMN_RATING FLOAT)")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addToCart(itemId:Int, name:String, price:Int, quantity:Int, totalPrice:Int, desc:String, rating:Float) : Long
    {
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COLUMN_ITEM_ID,itemId)
        cv.put(COLUMN_NAME,name)
        cv.put(COLUMN_ITEM_PRICE,price)
        cv.put(COLUMN_QUANTITY,quantity)
        cv.put(COLUMN_TOTAL_PRICE,totalPrice)
        cv.put(COLUMN_DESCRIPTION,desc)
        cv.put(COLUMN_RATING,rating)

        return db.insert(TABLE_NAME, null, cv)
    }

    fun getAllItemDetails() : MutableList<ItemDetail>
    {
        val db = this.readableDatabase
        val itemList = mutableListOf<ItemDetail>()

        val cursor: Cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ITEM_ID, COLUMN_NAME, COLUMN_ITEM_PRICE, COLUMN_QUANTITY, COLUMN_TOTAL_PRICE, COLUMN_DESCRIPTION, COLUMN_RATING),
            null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val itemId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ITEM_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val itemPrice = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ITEM_PRICE))
                val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                val totalPrice = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE))
                val desc = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_RATING))

                itemList.add(ItemDetail(itemId,name,itemPrice,quantity,totalPrice,desc,rating))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return itemList
    }

    fun deleteItemFromCart(itemId : Int)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"${COLUMN_ITEM_ID} = ?", arrayOf(itemId.toString()))
    }
}