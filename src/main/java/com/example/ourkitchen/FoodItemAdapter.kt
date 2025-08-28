package com.example.ourkitchen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodItemAdapter(
    private val foodItems: List<FoodItem>,
    private val onItemCheckedChanged: (FoodItem, Boolean) -> Unit
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    // Keep track of checkbox states
    private val checkedStates = mutableMapOf<Int, Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem = foodItems[position]

        holder.foodName.text = foodItem.name
        holder.foodDescription.text = foodItem.description ?: ""
        holder.foodPrice.text = "$${foodItem.price}"

        holder.addToCart.setOnCheckedChangeListener(null)
        holder.addToCart.isChecked = checkedStates[position] ?: false

        holder.addToCart.setOnCheckedChangeListener { _, isChecked ->
            checkedStates[position] = isChecked
            onItemCheckedChanged(foodItem, isChecked)
        }
    }

    override fun getItemCount(): Int = foodItems.size

    fun clearSelections() {
        checkedStates.clear()
        notifyDataSetChanged()
    }

    inner class FoodItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName: TextView = view.findViewById(R.id.tvFoodName)
        val foodDescription: TextView = view.findViewById(R.id.tvFoodDescription)
        val foodPrice: TextView = view.findViewById(R.id.tvFoodPrice)
        val addToCart: CheckBox = view.findViewById(R.id.cbAddToCart)
    }
}
