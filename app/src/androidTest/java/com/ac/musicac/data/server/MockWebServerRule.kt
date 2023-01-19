package com.ac.musicac.data.server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule: TestWatcher() {

    lateinit var server: MockWebServer

    override fun starting(description: Description) {
        server = MockWebServer()
        server.start(8080)
        server.enqueue(MockResponse().fromJson("token_response.json"))

    }

    override fun finished(description: Description) {
        server.shutdown()
    }

    fun runDispatcher() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                var response = ""
                if (path != null) when {
                    path.contains("6gQKAYf3TJM9sppw3AtbHH") -> response = "albums_response.json"
                    path.contains("/albums?limit=10") -> response = "artist_rosalia_albums_response.json"
                    path.contains("1Cs0zKBU1kc0i8ypK3B9ai") -> response = "artists_response.json"
                    path.contains("/artists/7ltDVBr6mKbRvohxheJ9h1") -> response = "artist_rosalia_response.json"
                    path.contains("/artists/716NhGYqD1jl2wI1Qkgq36") -> response = "artist_bizarrap_response.json"
                    path.contains("/artists/4q3ewBCX7sLwd24euuV69X") -> response = "artist_badbunny_response.json"
                    path.contains("/albums/3RQQmkQEvNCY4prGKE6oc5") -> response = "album_unverano_response.json"
                    path.contains("/albums/6jbtHi5R0jMXoliU2OS0lo") -> response = "album_rosalia_response.json"
                    path.contains("/albums/1wLB2bnCl2m5m9M9g8r93Y") -> response = "album_provenza_response.json"
                    path.contains("/albums/492U88qanlQnFgsfvwVHe8") -> response = "release_rosalia_bizcochito_response.json"
                    path.contains("/albums/4czxiqSwyeZK7y5r9GNWXP") -> response = "release_rosalia_despecha_response.json"
                    path.contains("/albums/3zbiiu3JTibw0esC7eoMXr") -> response = "release_rosalia_motomami_response.json"
                }
                return MockResponse().fromJson(response)

            }
        }
    }

}