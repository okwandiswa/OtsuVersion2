package com.example.otsuversiontwo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    /* Step 1: declare and initialize bottom navigation view */
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        /*When you click the button, you go straight to its fragment*/

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.customise -> {
                    replaceFragment(SettingsFragment())
                    true
                }
                R.id.feed -> {
                    replaceFragment(PetFragment())
                    true
                }
                R.id.calendar -> {
                    replaceFragment(CalendarFragment())
                    true
                }
                else -> false

            }
        }
        replaceFragment(HomeFragment())
    }

    /* Step 2: Create replace fragment method*/
    private fun replaceFragment(fragment: Fragment){
        /*Support fragment manager - Managing the fragments*/
        /*begin transaction - adding new fragments or replacing them*/
        /*replace - Current fragment with new fragment*/
        /*commit - applies the changes to the fragment*/
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment).commit()
    }

}