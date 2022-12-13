package com.ac.musicac.main.artist

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.di.qualifier.ArtistDummyIds
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtistsInstrumentationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    lateinit var artistDao: ArtistDao

    @Inject
    lateinit var dataSource: SpotifyDataSource

    private val artistsId: String = "7ltDVBr6mKbRvohxheJ9h1"

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))
        hiltRule.inject()
        // val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        // IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val artist = dataSource.getArtist(artistsId)
        artist.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("ROSALÍA", it.name)
        }
    }
}