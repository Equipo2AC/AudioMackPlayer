package com.ac.musicac.data.server

import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {

    lateinit var server: MockWebServer
    private var count = false

    override fun starting(description: Description) {
        server = MockWebServer()
        server.enqueue(MockResponse().fromJson("token_response.json"))
        server.start(8080)
    }

    override fun finished(description: Description) {

        Log.e("Artist Test", "${server.requestCount}")
        // server.close()
        server.dispatcher.shutdown()
        server.shutdown()

    }

    fun runDispatcher() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                var response = ""
                if (path != null) when {
                    path.contains("/albums?ids=3RQQmkQEvNCY4prGKE6oc5") -> response = "home_albums_response.json"
                    path.contains("/artists?ids=7ltDVBr6mKbRvohxheJ9h1") -> response = "home_artists_response.json"
                    path.endsWith("/albums?limit=10&offset=0") -> response = "artist_rosalia_albums_response.json"
                    path.contains("/artists/716NhGYqD1jl2wI1Qkgq36") -> response = "artist_bizarrap_response.json"
                    // path.contains("/artists/716NhGYqD1jl2wI1Qkgq36/albums?limit=10&offset=0") -> response = "artist_rosalia_albums_response.json"
                    // path.endsWith("/artists/716NhGYqD1jl2wI1Qkgq36/albums?limit=10&offset=0") -> response = "artist_rosalia_albums_response.json"

                    // path.contains("/artists/7ltDVBr6mKbRvohxheJ9h1") -> response = "artist_rosalia_response.json"

                    // path.contains("/artists/716NhGYqD1jl2wI1Qkgq36/albums?limit=10&offset=0") -> response = "artist_rosalia_albums_response.json"
                    // path.contains("/artists/4q3ewBCX7sLwd24euuV69X") -> response = "artist_badbunny_response.json"
                    // path.contains("/albums/3RQQmkQEvNCY4prGKE6oc5") -> response = "album_unverano_response.json"
                    // path.contains("/albums/6jbtHi5R0jMXoliU2OS0lo") -> response = "album_rosalia_response.json"
                    // path.contains("/albums/1wLB2bnCl2m5m9M9g8r93Y") -> response = "album_provenza_response.json"
                    // path.contains("/albums/492U88qanlQnFgsfvwVHe8") -> response = "release_rosalia_bizcochito_response.json"
                    // path.contains("/albums/4czxiqSwyeZK7y5r9GNWXP") -> response = "release_rosalia_despecha_response.json"
                    // path.contains("/albums/3zbiiu3JTibw0esC7eoMXr") -> response = "release_rosalia_motomami_response.json"


                    // path.contains("/token") -> response = "token_response.json"
                    // else -> response = "token_response.json"
                }

                return MockResponse().fromJson(response)
            }
        }
    }

}