package com.ac.musicac.ui.main.artist

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.ac.musicac.R
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.PopularArtist

class ArtistInfoView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    fun setArtist(artist: PopularArtist) = artist.apply {
        text = buildSpannedString {

            bold { append(context.getString(R.string.artist_name)) }
            appendLine(name)

            bold { append(context.getString(R.string.total_followers)) }
            appendLine(followers.toString())

            if (genres.isNotEmpty()) {
                bold { append(context.getString(R.string.genres)) }
                appendLine(genres.get(0))
            }
        }
    }
}