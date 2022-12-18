package com.ac.musicac.ui.main.search

import androidx.core.view.forEach
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Type
import com.ac.musicac.ui.main.releases.list.ReleasesAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("search_items")
fun RecyclerView.setItems(items: List<Item>?) {
    items?.let {
        when (adapter) {
            is ReleasesAdapter -> (adapter as ReleasesAdapter).submitList(it)
            is SearchAdapter -> (adapter as SearchAdapter).submitList(it)
            else -> {}
        }
    }
}

@BindingAdapter("search_items", "type", requireAll = true)
fun RecyclerView.setType(items: List<Item>?, type: Type?) {
    type?.let {
        (adapter as? SearchAdapter)?.apply {
            this.type = type
            submitList(items ?: arrayListOf())
        }
        layoutManager = when (type) {
            Type.ALBUM -> {
                setHasFixedSize(true)
                GridLayoutManager(context, 2)
            }
            Type.ARTIST -> {
                LinearLayoutManager(context)
            }
        }
    }
}

@BindingAdapter("onChooseTypeChanged")
fun ChipGroup.onChooseTypeChanged(listener: OnChooseTypeChanged?) {
    setOnCheckedStateChangeListener { group, checkedIds ->
        group.forEach {
            val chip = it as Chip
            if (checkedIds.contains(chip.id)) {
                val type = when (chip.text) {
                    context.getString(R.string.chip_album) -> Type.ALBUM
                    context.getString(R.string.chip_artist) -> Type.ARTIST
                    else -> null
                }
                type?.let {
                    listener?.onChooseTypeChanged(it)
                }
            }
        }
    }
}

interface OnChooseTypeChanged {
    fun onChooseTypeChanged(type: Type)
}