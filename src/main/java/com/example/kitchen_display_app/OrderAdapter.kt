package com.example.kitchen_display_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    private var items: List<KitchenOrder>,
    private val onAction: (KitchenOrder, String) -> Unit
) : RecyclerView.Adapter<OrderAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvOrder: TextView = view.findViewById(R.id.tvOrderNumber)
        val tvItems: TextView = view.findViewById(R.id.tvItems)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val btnProgress: Button = view.findViewById(R.id.btnProgress)
        val btnConfirm: Button = view.findViewById(R.id.btnConfirm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val order = items[position]

        holder.tvOrder.text = order.order_number
        holder.tvItems.text = order.items.joinToString(", ") { "${it.name} x${it.qty}" }
        holder.tvStatus.text = order.status

        holder.btnProgress.isEnabled = order.status == "pending"
        holder.btnConfirm.isEnabled = order.status == "progress"

        holder.btnProgress.setOnClickListener { onAction(order, "progress") }
        holder.btnConfirm.setOnClickListener { onAction(order, "confirmed") }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newList: List<KitchenOrder>) {
        items = newList
        notifyDataSetChanged()
    }
}
