package com.ac.musicac.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Item
import com.ac.musicac.ui.main.releases.list.ReleasesAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(albums: List<Item>?) {
    if (albums != null) {
        (adapter as? ReleasesAdapter)?.submitList(albums)
    }
}