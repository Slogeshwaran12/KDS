<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Order;

class OrderSeeder extends Seeder {
    public function run(): void {
        Order::create([
            'order_number' => 'K-1001',
            'items' => [
                ['name'=>'Veg Biryani','qty'=>1],
                ['name'=>'Gulab Jamun','qty'=>2],
            ],
            'status' => 'pending'
        ]);
        Order::create([
            'order_number' => 'K-1002',
            'items' => [
                ['name'=>'Paneer Butter Masala','qty'=>1],
            ],
            'status' => 'pending'
        ]);
    }
}
