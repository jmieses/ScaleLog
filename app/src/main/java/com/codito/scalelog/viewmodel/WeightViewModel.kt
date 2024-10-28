package com.codito.scalelog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.codito.scalelog.database.Weight
import com.codito.scalelog.database.WeightDatabase
import com.codito.scalelog.repository.WeightRepository
import kotlinx.coroutines.launch

class WeightViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WeightRepository
    val allWeights: LiveData<List<Weight>>

    init {
        // Get the WeightDao from the WeightDatabase
        val weightDao = WeightDatabase.getDatabase(application).weightDao()
        // Create the repository instance
        repository = WeightRepository(weightDao)
        // Get all weights from the repository
        allWeights = repository.allWeights
    }

    // Function to insert a new weight entry using a coroutine
    fun insert(weight: Weight) = viewModelScope.launch {
        repository.insert(weight)
    }
}
