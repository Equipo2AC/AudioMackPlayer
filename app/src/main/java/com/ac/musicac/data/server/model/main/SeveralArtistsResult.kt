package com.ac.musicac.data.server.model.main

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SeveralArtistsResult(
    @SerializedName("artists") val artists: List<ArtistViewResult>
)
