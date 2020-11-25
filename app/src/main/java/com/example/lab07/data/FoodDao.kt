package com.example.lab07.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: Food)

    @Query("SELECT * FROM food_table ORDER BY name ASC")
    fun getAllFoods(): LiveData<List<Food>>

    @Query("SELECT * FROM food_table WHERE id = :id")
    fun getFood(id: Int): LiveData<Food>

    @Delete
    suspend fun removeFood(food: Food)
}