package com.example.ourkitchen

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodItemAdapter
    private lateinit var btnPlaceOrder: Button

    private val api by lazy { RetrofitClient.api }
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private var menuItems: List<FoodItem> = emptyList()
    private val selectedItems = mutableSetOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvFoodItems)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)

        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchMenu()

        btnPlaceOrder.setOnClickListener {
            placeOrder()
        }
    }

    private fun fetchMenu() {
        coroutineScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { api.getMenu() }
                menuItems = response
                adapter = FoodItemAdapter(menuItems) { foodItem, isChecked ->
                    if (isChecked) selectedItems.add(foodItem)
                    else selectedItems.remove(foodItem)
                }
                recyclerView.adapter = adapter
            } catch (e: IOException) {
                showPopup("Network error")
            } catch (e: HttpException) {
                showPopup("Server error")
            }
        }
    }

    private fun placeOrder() {
        if (selectedItems.isEmpty()) {
            showPopup("Select at least one item")
            return
        }

        val orderItems = selectedItems.map { OrderItem(it.name, 1) }

        coroutineScope.launch {
            try {
                val orderRequest = OrderRequest(orderItems)
                withContext(Dispatchers.IO) { api.placeOrder(orderRequest) }

                // Show popup instead of Toast
                showPopup("Your order has been sent successfully!")

                selectedItems.clear()
                adapter.clearSelections()
            } catch (e: IOException) {
                showPopup("Network error")
            } catch (e: HttpException) {
                showPopup("Server error")
            }
        }
    }

    private fun showPopup(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Our Kitchen")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
