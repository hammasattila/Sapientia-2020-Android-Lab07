package com.example.lab07.data

import androidx.lifecycle.LiveData

class FoodRepository(private val  foodDao:FoodDao) {
    val getAllFoods: LiveData<List<Food>> = foodDao.getAllFoods()

    suspend fun addFood(food: Food){
        foodDao.addFood(food)
    }
}