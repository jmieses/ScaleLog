package com.codito.scalelog.repository

import androidx.lifecycle.LiveData
import com.codito.scalelog.database.Weight
import com.codito.scalelog.database.WeightDao

// Repository class to manage data operations for the Weight entity
class WeightRepository(private val weightDao: WeightDao) {

    // LiveData to get all weights from the database
    val allWeights: LiveData<List<Weight>> = weightDao.getAllWeights()

    // Function to insert a new weight entry into the database
    suspend fun insert(weight: Weight) {
        weightDao.insert(weight)
    }
}
