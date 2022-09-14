package com.ac.musicac.ui.main.search

import com.ac.musicac.domain.Albums
import com.ac.musicac.domain.Artists
import com.ac.musicac.domain.ExternalUrls
import com.ac.musicac.domain.Image
import com.ac.musicac.domain.Item
import com.ac.musicac.domain.Search

object SearchMocks {

    fun mockSearchAlbum() = Search(
        albums = Albums(
            href = "https://api.spotify.com/v1/search?query=estopa&type=album&offset=0&limit=20",
            items = listOf(
                Item(
                    id = 0,
                    itemId = "5ZqnEfVdEGmoPxtELhN7ai",
                    name = "Estopa",
                    href = "https://api.spotify.com/v1/artists/5ZqnEfVdEGmoPxtELhN7ai",
                    albumType = "album",
                    artists = "Estopa",
                    availableMarkets = arrayListOf(),
                    externalUrls =
                    ExternalUrls("https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai"),
                    image = Image(
                        height = 300,
                        width = 300,
                        url = "https://i.scdn.co/image/ab67616d00001e021a311570340a4f6530da158b"
                    ),
                    releaseDate = "1999-10-18",
                    releaseDatePrecision = "day",
                    totalTracks = 12,
                    type = "artist",
                    uri = "spotify:artist:5ZqnEfVdEGmoPxtELhN7ai",
                    followers = 0,
                    genres = arrayListOf()
                )
            ),
            limit = 20,
            next = "https://api.spotify.com/v1/search?query=estopa&type=album&offset=20&limit=20",
            offset = 0,
            previous = null,
            total = 102
        ),
        artists = null,
    )

    fun mockSearchArtist() = Search(
        albums = null,
        artists = Artists(
            href = "https://api.spotify.com/v1/search?query=estopa&type=album&offset=0&limit=20",
            items = listOf(
                Item(
                    id = 0,
                    itemId = "5ZqnEfVdEGmoPxtELhN7ai",
                    name = "Estopa",
                    href = "https://api.spotify.com/v1/artists/5ZqnEfVdEGmoPxtELhN7ai",
                    albumType = "",
                    artists = "Estopa",
                    availableMarkets = arrayListOf(),
                    externalUrls =
                    ExternalUrls("https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai"),
                    image = Image(
                        height = 320,
                        width = 320,
                        url = "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb"
                    ),
                    releaseDate = "1999-10-18",
                    releaseDatePrecision = "day",
                    totalTracks = 12,
                    type = "artist",
                    uri = "spotify:artist:5ZqnEfVdEGmoPxtELhN7ai",
                    followers = 1556285,
                    genres = arrayListOf(
                        "rumba",
                        "spanish pop"
                    )
                )
            ),
            limit = 20,
            next = "https://api.spotify.com/v1/search?query=estopa&type=artist&offset=20&limit=20",
            offset = 0,
            previous = null,
            total = 26
        ),
    )
}