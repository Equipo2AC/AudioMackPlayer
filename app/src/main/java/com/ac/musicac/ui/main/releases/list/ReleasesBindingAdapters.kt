package com.ac.musicac.ui.main.releases.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.domain.Item

@BindingAdapter("release_items")
fun RecyclerView.setItems(albums: List<Item>?) {
    if (albums != null) {
        (adapter as? ReleasesAdapter)?.submitList(albums)
    }
}