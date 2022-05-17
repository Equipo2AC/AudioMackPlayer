package com.ac.musicac.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentSplashBinding
import com.ac.musicac.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    private lateinit var splashState: SplashState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashState = buildSplashState()

        val binding = FragmentSplashBinding.bind(view)

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            if(it.error==null) splashState.navigate()
            binding.error = it.error?.let(splashState::errorToString)
        }


    }

}