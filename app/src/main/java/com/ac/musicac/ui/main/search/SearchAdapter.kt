package com.ac.musicac.ui.main.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac.musicac.R
import com.ac.musicac.databinding.ViewAlbumBinding
import com.ac.musicac.databinding.ViewArtistBinding
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Type
import com.ac.musicac.ui.common.basicDiffUtil
import com.ac.musicac.ui.common.inflate
import kotlin.properties.Delegates

class SearchAdapter(private val onAction: (SearchAction) -> Unit) :
    ListAdapter<Item, SearchAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    var type: Type by Delegates.observable(Type.ALBUM) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (type) {
            Type.ALBUM -> {
                AlbumViewHolder(
                    ViewAlbumBinding.bind(parent.inflate(R.layout.view_album, false))
                )
            }
            Type.ARTIST -> ArtistViewHolder(
                ViewArtistBinding.bind(parent.inflate(R.layout.view_artist, false))
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (type) {
            Type.ALBUM -> (holder as AlbumViewHolder).bind(item)
            Type.ARTIST -> (holder as ArtistViewHolder).bind(item)
        }
        holder.itemView.setOnClickListener {
            when (type) {
                Type.ALBUM -> onAction(SearchAction.OnClickAlbum(item))
                Type.ARTIST -> onAction(SearchAction.OnClickArtist(item))
            }
        }
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ArtistViewHolder(private val binding: ViewArtistBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
        }
    }

    class AlbumViewHolder(private val binding: ViewAlbumBinding) : ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item
        }
    }

}