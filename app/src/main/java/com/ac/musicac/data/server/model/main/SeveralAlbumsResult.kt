package com.ac.musicac.data.server.model.main

import com.ac.musicac.data.server.model.releases.AlbumsReleasesResult
import com.ac.musicac.data.server.model.releases.ItemResult
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SeveralAlbumsResult(
    @SerializedName("albums") val albums: List<AlbumViewResult>
)
