package com.example.lab07.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    val allFoods: LiveData<List<Food>>
    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getFoodDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        allFoods = repository.getAllFoods
    }

    fun addFood(food: Food) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(food)
        }
    }

    fun getFood(foodId: Int): LiveData<Food> {
        return repository.getFood(foodId)
    }
}