package com.ac.musicac.ui.navHostActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ac.musicac.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {

    private lateinit var navHostState: NavHostState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)

        navHostState = buildNavHostState()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_1 -> navHostState.navigateToHome()
                R.id.menu_item_2 -> navHostState.navigateToReleases()
                R.id.menu_item_3 -> navHostState.navigateToSearch()
            }
            true
        }
    }
}