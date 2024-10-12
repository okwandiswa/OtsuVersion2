package com.example.otsuversiontwo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*

class CalendarFragment : Fragment() {

    private val clickedDates = mutableSetOf<String>()  // Set to store clicked dates
    private val sharedPrefKey = "CLICKED_DATES"        // SharedPreferences key
    private lateinit var clickedCountTextView: TextView  // TextView for displaying clicked count
    private lateinit var gridView: GridView             // GridView to display dates
    private lateinit var adapter: CalendarAdapter       // Adapter for the GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout with GridView
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        gridView = view.findViewById(R.id.gridView)
        clickedCountTextView = view.findViewById(R.id.clickedCountTextView)

        // Load clicked dates from SharedPreferences
        loadClickedDates()

        // Update the count TextView initially
        updateClickedCount()

        // Generate the days of the current month
        val daysInMonth = getDaysInMonth()

        // Set the custom adapter for the GridView
        adapter = CalendarAdapter(requireContext(), daysInMonth, clickedDates)
        gridView.adapter = adapter

        // Handle click on any date for testing
        gridView.setOnItemClickListener { _, _, position, _ ->
            val clickedDate = daysInMonth[position]

            // Toggle clicked date: add if not present, remove if already clicked
            if (clickedDates.contains(clickedDate)) {
                clickedDates.remove(clickedDate)
            } else {
                clickedDates.add(clickedDate)
            }

            // Automatically reset if there is a missed day
            if (!isConsistent()) {
                clickedDates.clear()
                adapter.notifyDataSetChanged()
                saveClickedDates()
                updateClickedCount()
                Toast.makeText(requireContext(), "Missed a day, resetting count!", Toast.LENGTH_SHORT).show()
            } else {
                // Save the updated clicked dates to SharedPreferences
                saveClickedDates()
                // Update the count TextView
                updateClickedCount()
            }

            // Notify the adapter to refresh the GridView
            adapter.notifyDataSetChanged()
        }

        return view
    }

    // Function to save clicked dates to SharedPreferences
    private fun saveClickedDates() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("dates", clickedDates)
        editor.apply()
    }

    // Function to load clicked dates from SharedPreferences
    private fun loadClickedDates() {
        val sharedPreferences = requireContext().getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
        clickedDates.clear()
        clickedDates.addAll(sharedPreferences.getStringSet("dates", emptySet())!!)
    }

    // Function to update the TextView that displays the count of clicked dates
    private fun updateClickedCount() {
        val count = clickedDates.size
        clickedCountTextView.text = "Number of Days Consistent: $count"
    }

    // Function to check if the dates clicked are consistent without gaps
    private fun isConsistent(): Boolean {
        if (clickedDates.isEmpty()) return true

        val clickedDays = clickedDates.map { it.toInt() }.sorted()

        // Check for any gaps in the sequence
        for (i in 1 until clickedDays.size) {
            if (clickedDays[i] != clickedDays[i - 1] + 1) {
                return false
            }
        }
        return true
    }

    // Generate a list of day numbers for the current month
    private fun getDaysInMonth(): List<String> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..maxDay) {
            days.add(i.toString()) // Add each day of the month as a string (e.g., "1", "2", "3", ...)
        }

        return days
    }
}



