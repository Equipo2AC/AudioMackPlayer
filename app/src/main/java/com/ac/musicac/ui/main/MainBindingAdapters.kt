package com.ac.musicac.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Item
import com.ac.musicac.ui.main.releases.ReleasesAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(movies: List<Item>?) {
    if (movies != null) {
        (adapter as? ReleasesAdapter)?.submitList(movies)
    }
}