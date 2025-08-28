package com.example.ourkitchen

data class FoodItem(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val image_url: String? = null
)

data class OrderItem(
    val name: String,
    val qty: Int
)

data class OrderRequest(
    val items: List<OrderItem>
)

data class KitchenOrder(
    val id: Int,
    val status: String,
    val estimatedTime: String
)
