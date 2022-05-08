package com.ac.musicac.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ac.musicac.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}