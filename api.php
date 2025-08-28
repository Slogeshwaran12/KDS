<?php

use App\Http\Controllers\Api\OrderController;
use App\Http\Controllers\Api\FoodItemController;
use App\Http\Controllers\Api\CustomerOrderController;

Route::get('/orders', [OrderController::class, 'index']);
Route::patch('/orders/{order}/status', [OrderController::class, 'updateStatus']);
Route::get('/menu', [FoodItemController::class, 'index']);
Route::post('/orders', [CustomerOrderController::class, 'store']);
