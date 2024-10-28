package com.codito.scalelog.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the Weight entity for Room Database
@Entity(tableName = "weight_table")
data class Weight(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Primary key, auto-generated
    val weight: Double, // The weight value entered by the user
    val timestamp: Long = System.currentTimeMillis() // Timestamp to record when the weight was added
)
