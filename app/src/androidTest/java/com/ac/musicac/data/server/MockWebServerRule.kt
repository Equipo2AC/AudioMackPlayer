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

    fun runHomeDispatcher() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                var response = ""
                if (path != null) when {
                    // path.contains("/token") -> response = "token_response.json"
                    path.contains("6gQKAYf3TJM9sppw3AtbHH") -> response = "albums_response.json"
                    path.contains("/albums?limit=10") -> response = "artist_rosalia_albums_response.json"
                    path.contains("1Cs0zKBU1kc0i8ypK3B9ai") -> response = "artists_response.json"
                    path.contains("/artists/7ltDVBr6mKbRvohxheJ9h1") -> response = "artist_rosalia_response.json"
                    path.contains("?market=ES") -> response = "release_albums_response.json"
                    // else -> response = "artist_rosalia_response.json"
                }
                /*if (path != null) {
                    when {
                        // path.contains("/token") -> response = "token_response.json"
                        path.contains("6gQKAYf3TJM9sppw3AtbHH") -> response = "albums_response.json"
                        path.contains("/albums?limit=10") -> response = "artist_rosalia_albums_response.json"
                        path.contains("1Cs0zKBU1kc0i8ypK3B9ai") -> response = "artists_response.json"
                        path.contains("/artists/7ltDVBr6mKbRvohxheJ9h1") -> response = "artist_rosalia_response.json"
                        // else -> response = "artist_rosalia_response.json"
                    }
                } else {
                    response = "artist_rosalia_response.json"
                }*/
                return MockResponse().fromJson(response)

            }
        }
    }

    fun runArtistDispatcher() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = request.path
                var response = ""
                if (path != null) when {
                    // path.contains("/token") -> response = "token_response.json"
                    path.contains("/albums?ids=3RQQ") -> response = "albums_response.json"
                    path.contains("/albums?limit=10") -> response = "albums_response.json"
                    // path.contains("/artists?ids=7ltD") -> response = "artist_response.json"
                    path.contains("/artists/7ltDVB") -> response = "artist_rosalia_response.json"
                    else -> response = "artist_rosalia_response.json"
                }
                return MockResponse().fromJson(response)
            }
        }
    }

}