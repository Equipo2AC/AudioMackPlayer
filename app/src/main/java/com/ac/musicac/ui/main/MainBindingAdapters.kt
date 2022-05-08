package com.ac.musicac.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Albums
import com.ac.musicac.ui.main.releases.ReleasesAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(movies: List<Albums>?) {
    if (movies != null) {
        (adapter as? ReleasesAdapter)?.submitList(movies)
    }
}