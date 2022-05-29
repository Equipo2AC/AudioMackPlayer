package com.ac.musicac.ui.main.releases.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.ac.musicac.R
import com.ac.musicac.domain.releases.Release

class ReleaseDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setRelease(movie: Release) = movie.apply {
        text = buildSpannedString {


            bold { append(context.getString(R.string.release_detail_number_tracks)) }
            appendLine(totalTracks)

            bold { append(context.getString(R.string.release_detail_date)) }
            appendLine(releaseDate)

            bold { append(context.getString(R.string.release_detail_popularity)) }
            appendLine(popularity.toString())

            bold { append(context.getString(R.string.release_detail_copyrights)) }
            appendLine(copyrights.toString())

        }
    }
}