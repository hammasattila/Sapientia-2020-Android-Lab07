package com.example.lab07.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var foodDatabase: FoodDatabase? = null

        fun getFoodDatabase(context: Context): FoodDatabase {
            val tmpDb = foodDatabase
            if (tmpDb != null) {
                return tmpDb
            }

            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "food_database"
                ).build()
                foodDatabase = db

                return db
            }
        }
    }
}