package com.example.otsuversiontwo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    // LiveData to track if the pet has been fed
    private val _isPetFed = MutableLiveData<Boolean>()
    val isPetFed: LiveData<Boolean> get() = _isPetFed

    // Function to update pet's fed state
    fun feedPet() {
        _isPetFed.value = true
    }

    // LiveData to track the current date when pet is fed (for calendar)
    private val _currentFedDate = MutableLiveData<String>()
    val currentFedDate: LiveData<String> get() = _currentFedDate

    // Function to update the current date in the calendar
    fun updateFedDate(date: String) {
        _currentFedDate.value = date
    }
}
