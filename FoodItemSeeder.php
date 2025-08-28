<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\FoodItem;

class FoodItemSeeder extends Seeder
{
    public function run()
    {
        $items = [
            ['name' => 'Veg Biryani', 'description' => 'Spiced rice with vegetables', 'price' => 8.99, 'image_url' => null],
            ['name' => 'Paneer Butter Masala', 'description' => 'Creamy tomato gravy with paneer', 'price' => 10.50, 'image_url' => null],
            ['name' => 'Gulab Jamun', 'description' => 'Sweet syrupy dessert', 'price' => 3.00, 'image_url' => null],
            ['name' => 'Chicken Tikka Masala', 'description' => 'Grilled chicken pieces in spicy curry', 'price' => 12.99, 'image_url' => null],
            ['name' => 'Naan Bread', 'description' => 'Soft Indian flatbread', 'price' => 1.99, 'image_url' => null],
            ['name' => 'Masala Dosa', 'description' => 'Crispy crepe stuffed with spiced potatoes', 'price' => 7.50, 'image_url' => null],
            ['name' => 'Samosa', 'description' => 'Fried pastry with spicy filling', 'price' => 2.50, 'image_url' => null],
            ['name' => 'Lassi', 'description' => 'Sweet yogurt drink', 'price' => 3.50, 'image_url' => null],
            ['name' => 'Chole Bhature', 'description' => 'Spiced chickpeas with fried bread', 'price' => 9.99, 'image_url' => null],
            ['name' => 'Pani Puri', 'description' => 'Crispy hollow puris with flavored water', 'price' => 4.00, 'image_url' => null],
        ];

        foreach ($items as $item) {
            FoodItem::create($item);
        }
    }
}
