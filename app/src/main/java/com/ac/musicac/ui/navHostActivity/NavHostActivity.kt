package com.ac.musicac.ui.navHostActivity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ac.musicac.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navHostState: NavHostState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)

//        navHostState = buildNavHostState()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_1 -> {
                    findNavController(R.id.nav_host_splash_fragment).navigate(R.id.nav_graph_home )
                }

                R.id.item_2 -> {
                    findNavController(R.id.nav_host_splash_fragment).navigate(R.id.nav_graph_releases)
                }

            }
            true
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
}