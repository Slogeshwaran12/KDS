package com.example.kitchen_display_app

data class Item(
    val name: String,
    val qty: Int
)

data class KitchenOrder(
    val id: Long,
    val order_number: String,
    val items: List<Item>,
    var status: String
)
