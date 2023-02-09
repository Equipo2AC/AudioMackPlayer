package com.ac.musicac.ui.main.artist

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.italic
import com.ac.musicac.R
import com.ac.musicac.domain.PopularArtist
import java.text.DecimalFormat

class ArtistInfoView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    fun setArtist(artist: PopularArtist) = artist.apply {
        text = buildSpannedString {

            bold { append(context.getString(R.string.artist_name)) }
            italic { appendLine(name) }

            if(genres.isNotEmpty()) {
                bold { append(context.getString(R.string.total_followers)) }
                italic { appendLine(DecimalFormat().format(followers.total)) }
            } else {
                bold { append(context.getString(R.string.total_followers)) }
                italic { append(DecimalFormat().format(followers.total)) }
            }

            if (genres.isNotEmpty()) {
                bold { append(context.getString(R.string.genres)) }
                italic { append(genres.get(0)) }
            }
        }
    }
}