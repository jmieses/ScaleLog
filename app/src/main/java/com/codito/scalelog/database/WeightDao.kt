package com.codito.scalelog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Define the Data Access Object for the Weight entity
@Dao
interface WeightDao {

    // Insert a new weight record into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weight: Weight)

    // Get all weight records, ordered by timestamp descending
    @Query("SELECT * FROM weight_table ORDER BY timestamp DESC")
    fun getAllWeights(): LiveData<List<Weight>>
}
