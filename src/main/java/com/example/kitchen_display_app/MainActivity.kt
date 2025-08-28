package com.example.kitchen_display_app

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: OrderAdapter
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[OrderViewModel::class.java]

        // Setup RecyclerView and Adapter with lambda callback for button actions
        adapter = OrderAdapter(emptyList()) { order, status ->
            viewModel.setStatus(order, status)
        }

        val rvOrders = findViewById<RecyclerView>(R.id.rvOrders)
        rvOrders.layoutManager = LinearLayoutManager(this)
        rvOrders.adapter = adapter

        // Refresh Button setup
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        btnRefresh.setOnClickListener {
            viewModel.fetchPending()
        }

        // Observe LiveData from ViewModel and submit updated list to adapter
        viewModel.orders.observe(this) { list ->
            adapter.submitList(list)
        }

        // Initial fetch on start
        viewModel.fetchPending()
    }
}
