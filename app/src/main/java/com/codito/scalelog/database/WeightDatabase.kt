package com.codito.scalelog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define the WeightDatabase class for Room
@Database(entities = [Weight::class], version = 1, exportSchema = false)
abstract class WeightDatabase : RoomDatabase() {

    // Abstract function to get the WeightDao
    abstract fun weightDao(): WeightDao

    companion object {
        @Volatile
        private var INSTANCE: WeightDatabase? = null

        // Function to get the database instance
        fun getDatabase(context: Context): WeightDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeightDatabase::class.java,
                    "weight_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
