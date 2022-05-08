package com.ac.musicac.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ac.musicac.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_nav_host)
    }
}