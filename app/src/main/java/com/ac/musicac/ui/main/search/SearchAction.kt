package com.ac.musicac.ui.main.search

import com.ac.musicac.domain.Item

sealed class SearchAction(val item : Item) {
    class OnClickAlbum(item: Item) : SearchAction(item)
    class OnClickArtist(item: Item) : SearchAction(item)
}