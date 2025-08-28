package com.example.kitchen_display_app

import retrofit2.http.*

interface ApiService {
    @GET("orders")
    suspend fun getOrders(@Query("status") status: String = "pending"): List<KitchenOrder>

    @PATCH("orders/{id}/status")
    suspend fun updateOrderStatus(
        @Path("id") id: Long,
        @Body body: Map<String, String>
    ): KitchenOrder
}
