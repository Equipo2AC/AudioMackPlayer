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
        // runDispatcher()
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
                    // path.contains("/token") -> response = "token_response.json"
                    path.contains("/albums") -> response = "albums_response.json"
                    path.contains("/artists") -> response = "artists_response.json"
                    path.contains("/artists/7ltDVB") -> response = "artist_rosalia_response.json"
                    // else -> response = "artist_rosalia_response.json"
                }
                return MockResponse().fromJson(response)
            }
        }
    }

}