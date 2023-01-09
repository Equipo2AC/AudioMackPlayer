package com.ac.musicac.main.artist

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.di.qualifier.OnlyArtistDummyId
import com.ac.musicac.ui.main.artist.ArtistFragment
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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

    /*@get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)*/

    /*@get:Rule(order = 3)
    val scenario = launchFragmentInContainer<ArtistFragment>(
        initialState = Lifecycle.State.INITIALIZED
    )*/

    @Inject
    lateinit var artistDao: ArtistDao

    @Inject
    lateinit var dataSource: SpotifyDataSource

    @Inject
    @OnlyArtistDummyId
    lateinit var artistsId: String

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<ArtistFragment>()
        // scenario.moveToState(Lifecycle.State.RESUMED)
        mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))
        hiltRule.inject()
        val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun click_an_artist_album_navigates_to_album_detail() {

        // scenario.recreate()
        Thread.sleep(2000)

        onView(withId(R.id.recycler_artist))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Thread.sleep(5000)

        onView(withId(R.id.artist_toolbar)).check(
            ViewAssertions.matches(
                ViewMatchers.hasDescendant(
                    ViewMatchers.withText("Bizarrap")
                )
            )
        )
    }

    /*@Test
    fun check_mock_server_is_working() = runTest {
        val artist = dataSource.getArtist(artistsId)
        artist.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("ROSALÍA", it.name)
        }
    }*/


}