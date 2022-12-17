package com.ac.musicac.main.home

import android.util.Log
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.buildDatabaseArtist
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.datasource.SpotifyAuthenticationDataSource
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.di.qualifier.ArtistDummyIds
import com.ac.musicac.domain.Token
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeArtistsInstrumentationTest {

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

    // @Inject
    // lateinit var artistDao: ArtistDao

    @Inject
    lateinit var authDataSource: SpotifyAuthenticationDataSource

    @Inject
    lateinit var dataSource: SpotifyDataSource

    @Inject
    @ArtistDummyIds
    lateinit var artistsIds: String

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(MockResponse().fromJson("token_response.json"))
        mockWebServerRule.server.enqueue(MockResponse().fromJson("artists_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("albums_response.json"))
        hiltRule.inject()
        val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    /*@Test
    fun check_4_IM_items_db() = runTest {
        artistDao.insertAllArtist(buildDatabaseArtist(1, 2, 3, 4))
        assertEquals(4, artistDao.artistCount())
    }

    @Test
    fun check_6_IM_items_db()  = runTest {
        artistDao.insertAllArtist(buildDatabaseArtist(5, 6, 7, 8, 9, 10))
        assertEquals(6, artistDao.artistCount())
    }*/

    @Test
    fun check_mock_auth_server_is_working() = runTest {

        val token = authDataSource.getToken()
        var newToken : Token? = null
        token.fold({ throw Exception(it.toString()) }) {
            newToken = Token(
                it.value,
                it.type,
                it.expirationDate
            )
        }
        assertEquals("Bearer", newToken?.type)
    }

    /*@Test
    fun check_mock_server_is_working() = runTest {
        val artists = dataSource.getSeveralArtist(artistsIds)
        artists.fold({ throw Exception(it.toString()) }) {
            assertEquals("Rosalia", it.artists.get(0).name)
        }
        // assertEquals("Rosalia", "Rosalia")
    }*/

}