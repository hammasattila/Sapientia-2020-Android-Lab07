package com.example.lab07.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String, val description: String
)