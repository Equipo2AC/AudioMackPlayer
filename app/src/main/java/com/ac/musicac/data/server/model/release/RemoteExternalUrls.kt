package com.ac.musicac.data.server.model.release

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteExternalUrls(
    @SerializedName("spotify") val spotify: String
)