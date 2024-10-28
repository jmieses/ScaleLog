package com.codito.scalelog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codito.scalelog.R
import com.codito.scalelog.database.Weight
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeightAdapter : RecyclerView.Adapter<WeightAdapter.WeightViewHolder>() {

    private var weights = emptyList<Weight>() // List to hold weight data

    // ViewHolder class to represent each item in the RecyclerView
    class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weightTextView: TextView = itemView.findViewById(R.id.weight_text) // TextView for weight
        val dateTextView: TextView = itemView.findViewById(R.id.date_text) // TextView for timestamp
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weight_item, parent, false) // Inflate the layout for each item
        return WeightViewHolder(itemView)
    }

    // Bind data to the views (invoked by the layout manager)
    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        val currentWeight = weights[position] // Get the current weight item
        holder.weightTextView.text = "${currentWeight.weight} lbs" // Set the weight value
        holder.dateTextView.text = formatDate(currentWeight.timestamp) // Format and set the timestamp
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = weights.size

    // Update the list of weights and notify the adapter of data changes
    fun setWeights(weights: List<Weight>) {
        this.weights = weights
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    // Helper function to format the timestamp to a readable date
    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
