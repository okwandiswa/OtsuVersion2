package com.example.otsuversiontwo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class CalendarAdapter(
    private val context: Context,               // Context to inflate the layout
    private val daysInMonth: List<String>,       // List of days to be displayed
    private val clickedDates: Set<String>        // Set of clicked dates to highlight
) : BaseAdapter() {

    // Returns the total number of items in the list (days in the month)
    override fun getCount(): Int {
        return daysInMonth.size
    }

    // Returns the day at a specific position
    override fun getItem(position: Int): Any {
        return daysInMonth[position]
    }

    // Returns the item ID for a specific position (not used here)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // Creates and returns the view for each day (each grid item)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inflate the layout for each day item
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = convertView ?: inflater.inflate(R.layout.calendar_day_item, parent, false)

        // Get reference to the TextView in the day item layout
        val dayTextView = view.findViewById<TextView>(R.id.calendarDay)
        val day = daysInMonth[position]

        // Set the text (day number) for the current grid cell
        dayTextView.text = day

        // Highlight the clicked dates by checking if the date is in the clickedDates set
        if (clickedDates.contains(day)) {
            dayTextView.setBackgroundColor(Color.YELLOW)  // Highlight color for clicked date
        } else {
            dayTextView.setBackgroundColor(Color.WHITE)   // Default background for unclicked dates
        }

        return view
    }
}
