<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\FoodItem;

class FoodItemController extends Controller {
    public function index() {
        $items = FoodItem::all();
        return response()->json($items);
    }
}
