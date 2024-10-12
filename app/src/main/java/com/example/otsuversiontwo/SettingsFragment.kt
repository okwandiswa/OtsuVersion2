package com.example.otsuversiontwo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    private lateinit var otsuImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Get references to the ImageViews and TextViews from the layout
        val otsuImage: ImageView = view.findViewById(R.id.choose_otsu)
        val otsetteImage: ImageView = view.findViewById(R.id.choose_otsette)

        /* Set up listeners for Otsu and Otsette image selection */
        otsuImage.setOnClickListener {
            selectOtsu("Otsu")
        }
        otsetteImage.setOnClickListener {
            selectOtsu("Otsette")
        }

        return view
    }

    // Function to handle Otsu or Otsette selection
    private fun selectOtsu(otsuType: String) {
        // Get the entered name from the EditText field
        val enteredName = view?.findViewById<EditText>(R.id.name_input)?.text.toString()

        if (enteredName.isEmpty()) {
            // Show a toast if no name is entered
            Toast.makeText(activity, "Please enter a name for your Otsu!", Toast.LENGTH_SHORT).show()
            return
        }

        // Show the confirmation dialog with the entered name and selected Otsu type
        showConfirmationDialog(enteredName, otsuType)
    }

    // Function to display the confirmation dialog
    private fun showConfirmationDialog(name: String, otsuType: String) {
        // Create the dialog builder
        val builder = AlertDialog.Builder(requireContext())

        // Set the dialog title and message
        builder.setTitle("Otsu Selection")
        builder.setMessage("Name: $name\nYou chose: $otsuType")

        // Set the appropriate image based on the selection
        val imageView = ImageView(requireContext())
        when (otsuType) {
            "Otsu" -> imageView.setImageResource(R.drawable.male)
            "Otsette" -> imageView.setImageResource(R.drawable.female)
        }

        // Add the image to the dialog
        builder.setView(imageView)

        // Set up buttons
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        // Create and show the dialog
        val dialog = builder.create()
        dialog.show()
    }
}

