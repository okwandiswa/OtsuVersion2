package com.example.otsuversiontwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import java.text.SimpleDateFormat
import java.util.*

class PetFragment : Fragment() {

    // Get the shared ViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet, container, false)

        // Find the ImageView (for the bowl) and set up the onClick listener
        val bowlImageView: ImageView = view.findViewById(R.id.bowl_image)
        bowlImageView.setOnClickListener {
            // Feed the pet
            feedPet()

            // Update the calendar with the current date
            val currentDate = getCurrentDate()
            sharedViewModel.updateFedDate(currentDate)
        }

        return view
    }

    private fun feedPet() {
        sharedViewModel.feedPet()  // Set pet as fed in the ViewModel
    }

    // Get current date as a string (e.g., "12" for 12th of the month)
    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("d", Locale.getDefault())
        return sdf.format(Date())
    }
}
