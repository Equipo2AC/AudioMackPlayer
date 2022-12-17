package com.ac.musicac.testshared

import com.ac.musicac.domain.*

object Mocks {

    fun mockArtists() = Artists(
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
    )

    fun mockAlbums() = Albums(
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
    )

    fun mockSearchAlbum() = Search(
        albums = mockAlbums(),
        artists = null,
    )

    fun mockSearchArtist() = Search(
        albums = null,
        artists = mockArtists(),
    )

    fun mockUser() : User =  User(
        id = "1",
        displayName = "Adrián",
        followers = 0,
        imageUrl = ""
    )

    fun mockPopularArtist() :PopularArtist = PopularArtist(
        id = 0,
        externalUrls = ExternalUrls("https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai"),
        followers = Followers("", 1556285),
        genres = listOf("pop","rock"),
        href = "https://open.spotify.com",
        artistId = "7ltDVBr6mKbRvohxheJ9h1",
        images = listOf(Image(
            height = 320,
            width = 320,
            url = "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb")),
        name = "Artist Name",
        popularity = 75,
        type = "artist",
        uri = "https://i.scdn.co/image/"
    )

    fun mockPopularAlbums() :AlbumView = AlbumView(
        id = 0,
        album_type = "album",
        artists = listOf(),
        copyrights = listOf(),
        external_ids = ExternalIds(""),
        external_urls = ExternalUrls("https://open.spotify.com/artist/5ZqnEfVdEGmoPxtELhN7ai"),
        genres = listOf("pop","rock"),
        href = "https://open.spotify.com",
        albumId = "6jbtHi5R0jMXoliU2OS0lo",
        image = "https://i.scdn.co/image/ab676161000051743e5de222aa09ea8c106f2bbb",
        label = "Label here 0",
        name = "Artist Name",
        popularity = 75,
        release_date = "1999-10-18",
        release_date_precision = "1999-10-18",
        total_tracks = 20,
        tracks = Tracks(listOf(), 0),
        type = "artist",
        uri = "https://i.scdn.co/image/"
    )

    fun mockReleases() : Releases = Releases(
       albums = mockAlbums()
    )

    fun mockSeveralAlbums() :SeveralAlbums = SeveralAlbums(listOf(mockPopularAlbums()))

    fun mockSeveralArtists() : SeveralArtist = SeveralArtist(listOf(mockPopularArtist()))

}