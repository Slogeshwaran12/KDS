package com.example.ourkitchen

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("menu")
    suspend fun getMenu(): List<FoodItem>

    @POST("orders")
    suspend fun placeOrder(@Body order: OrderRequest): KitchenOrder
}
