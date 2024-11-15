package com.example.shopify_app

import com.example.shopify_app.Model.Item
import com.example.shopify_app.Model.ItemDetail
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("67b97871-3b4e-4f67-83c6-8af3eaa27411")
    fun getDataList(): Call<MutableList<ItemDetail>>

}