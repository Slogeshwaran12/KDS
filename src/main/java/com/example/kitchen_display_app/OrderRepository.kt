package com.example.kitchen_display_app

class OrderRepository(private val api: ApiService) {
    suspend fun getPendingOrders() = api.getOrders("pending")
    suspend fun updateStatus(id: Long, status: String) =
        api.updateOrderStatus(id, mapOf("status" to status))
}
