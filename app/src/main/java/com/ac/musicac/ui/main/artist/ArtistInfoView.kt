package com.ac.musicac.ui.main.artist

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.ac.musicac.R
import com.ac.musicac.domain.Item

class ArtistInfoView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    fun setArtist(artist: Item) = artist.apply {
        text = buildSpannedString {

            bold { append(context.getString(R.string.artist_name)) }
            appendLine(name)

            /*bold { append(context.getString(R.string.release_detail_date)) }
            appendLine(releaseDate)*/

            /*bold { append(context.getString(R.string.release_detail_popularity)) }
            appendLine(popularity.toString())*/

            /*bold { append(context.getString(R.string.release_detail_artists)) }
            appendLine(artists)*/

        }
    }

}