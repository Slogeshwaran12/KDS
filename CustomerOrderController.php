<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Order;
use Illuminate\Http\Request;
use Illuminate\Support\Str;

class CustomerOrderController extends Controller {
    public function store(Request $request) {
        $data = $request->validate([
            'items' => 'required|array|min:1',
            'items.*.name' => 'required|string',
            'items.*.qty' => 'required|integer|min:1',
        ]);

        $orderNumber = 'C-' . strtoupper(Str::random(6));

        $order = Order::create([
            'order_number' => $orderNumber,
            'items' => $data['items'],
            'status' => 'pending'
        ]);

        return response()->json($order, 201);
    }
}
