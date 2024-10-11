package com.example.otsuversiontwo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class PetFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pet, container, false)

        // Get the selected Otsu type and entered name from the arguments passed by the previous fragment
        val otsuType = arguments?.getString("otsuType") ?: "Unknown"
        val enteredName = arguments?.getString("enteredName") ?: "No name"


        // Find the TextViews and ImageView from the layout
        val nameTextView: TextView = view.findViewById(R.id.entered_name_text)
        val otsuTextView: TextView = view.findViewById(R.id.selected_otsu_text)
        val otsuImageView: ImageView = view.findViewById(R.id.selected_otsu_image)

        // Set the text to show the entered name and selected Otsu type
        nameTextView.text = "Name: $enteredName"
        otsuTextView.text = "You chose: $otsuType"

        // Set the appropriate image based on the selection
        if (otsuType == "Otsu") {
            otsuImageView.setImageResource(R.drawable.male)
        } else if (otsuType == "Otsette") {
            otsuImageView.setImageResource(R.drawable.female)
        }

        return view

    }

    companion object {
        // To allow creating the fragment and passing the selected Otsu type as an argument
        fun newInstance(otsuType: String, enteredName:String): PetFragment {
            val fragment = PetFragment()
            val args = Bundle()
            args.putString("otsuType", otsuType)
            args.putString("enteredName", enteredName)
            fragment.arguments = args
            return fragment
        }
    }
}