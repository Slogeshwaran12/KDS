<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Order;
use Illuminate\Http\Request;

class OrderController extends Controller
{
    // GET /api/orders?status=pending
    public function index(Request $request) {
        $status = $request->query('status', 'pending');
        $orders = Order::where('status', $status)->orderBy('created_at','asc')->get();
        return response()->json($orders);
    }

    // PATCH /api/orders/{order}/status
    public function updateStatus(Request $request, Order $order) {
        $data = $request->validate([
            'status' => 'required|in:pending,progress,confirmed'
        ]);
        $order->update(['status' => $data['status']]);
        // optional: broadcast an event to notify kitchen displays in real time
        return response()->json($order);
    }
}
