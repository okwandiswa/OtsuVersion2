package com.example.otsuversiontwo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Get references to the ImageViews from the layout
        val otsuImage: ImageView = view.findViewById(R.id.choose_otsu)
        val otsetteImage: ImageView = view.findViewById(R.id.choose_otsette)

        /*set up listeners */
        otsuImage.setOnClickListener {
            navigateToPetFragment("Otsu")
        }
        otsetteImage.setOnClickListener {
            navigateToPetFragment("Otsette")
        }

        return  view
    }
    // Function to navigate to PetFragment
    private fun navigateToPetFragment(otsuType: String) {
        val enteredName = view?.findViewById<EditText>(R.id.name_input)?.text.toString()

        val petFragment = PetFragment.newInstance(otsuType, enteredName)

        // Begin a fragment transaction to replace the current fragment
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container_view, petFragment)
            ?.addToBackStack(null)
            ?.commit()

        //Show a toast for feedback
        Toast.makeText(activity, "You chose $otsuType!", Toast.LENGTH_SHORT).show()

    }
}