package com.codito.scalelog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codito.scalelog.database.Weight
import com.codito.scalelog.viewmodel.WeightViewModel
import com.codito.scalelog.adapter.WeightAdapter
import com.codito.scalelog.R

class MainActivity : AppCompatActivity() {

    // Declare UI components and ViewModel
    private lateinit var weightInput: EditText // Input field for user to enter weight
    private lateinit var addButton: Button // Button to add weight to the list
    private lateinit var weightHistoryRecyclerView: RecyclerView // RecyclerView to display weight history
//    private val weightViewModel: WeightViewModel by viewModels() // ViewModel to manage UI-related data
    private val weightViewModel: WeightViewModel by lazy { ViewModelProvider(this).get(WeightViewModel::class.java) }
    private lateinit var weightAdapter: WeightAdapter // Adapter for RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display for a more immersive UI experience
        setContentView(R.layout.activity_main) // Set the layout for the activity

        // Adjust view padding to account for system bars (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements by finding them by their IDs
        weightInput = findViewById(R.id.et_weight_input) // EditText for user input
        addButton = findViewById(R.id.btn_add_weight) // Button to add weight
        weightHistoryRecyclerView = findViewById(R.id.rv_weight_history) // RecyclerView to display weight history

        // Set up RecyclerView to display the weight history
        weightAdapter = WeightAdapter() // Initialize the adapter
        weightHistoryRecyclerView.layoutManager = LinearLayoutManager(this) // Set layout manager to arrange items vertically
        weightHistoryRecyclerView.adapter = weightAdapter // Set the adapter for RecyclerView

        // Set up button click listener to add weight to the database
        addButton.setOnClickListener {
            val weightText = weightInput.text.toString() // Get the text entered by the user
            if (weightText.isNotEmpty()) { // Check if input is not empty
                val weight = weightText.toDoubleOrNull() // Try to convert input to a Double
                if (weight != null && weight > 0) { // If conversion is successful
                    val newWeight = Weight(weight = weight) // Create a new Weight object
                    weightViewModel.insert(newWeight) // Insert the weight into the database via ViewModel
                    weightInput.text.clear() // Clear the input field after successful insertion
                    Toast.makeText(this, "Weight added: $weight lbs", Toast.LENGTH_SHORT).show() // Show confirmation message
                } else { // If conversion fails
                    Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show() // Show error message for invalid input
                }
            } else { // If input is empty
                Toast.makeText(this, "Weight input cannot be empty", Toast.LENGTH_SHORT).show() // Show error message for empty input
            }
        }

        // Observe weight data from ViewModel and update RecyclerView when data changes
        weightViewModel.allWeights.observe(this, Observer { weights ->
            weights?.let { weightAdapter.setWeights(it) } // Update adapter with new weight data
        })
    }
}