package com.example.kitchen_display_app

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val repo = OrderRepository(RetrofitClient.api)

    private val _orders = MutableLiveData<List<KitchenOrder>>(emptyList())
    val orders: LiveData<List<KitchenOrder>> = _orders

    fun fetchPending() {
        viewModelScope.launch {
            try {
                _orders.value = repo.getPendingOrders()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setStatus(order: KitchenOrder, newStatus: String, onDone: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                val updated = repo.updateStatus(order.id, newStatus)
                _orders.value = _orders.value?.map {
                    if (it.id == updated.id) updated else it
                }
                onDone?.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
