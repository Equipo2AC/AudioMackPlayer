package com.ac.musicac.ui.main.releases.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ac.musicac.R
import com.ac.musicac.domain.Error
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildReleaseDetailState (
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope
) = ReleaseDetailState(context, scope)

class ReleaseDetailState(
    private val context: Context,
    private val scope: CoroutineScope
) {


    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}