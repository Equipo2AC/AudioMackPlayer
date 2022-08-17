package com.ac.musicac.data.server.model.release

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteImage(
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)