package com.ac.musicac.main.artist

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.server.*
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.di.launchFragmentInHiltContainer
import com.ac.musicac.di.qualifier.OnlyArtistDummyId
import com.ac.musicac.ui.main.artist.ArtistFragment
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.*
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

    // @get:Rule(order = 3)
    // val scenario = ActivityScenario.launch(NavHostActivity::class.java)

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)


    /*@get:Rule(order = 3)
    val scenario = launchFragmentInHiltContainer<ArtistFragment>()*/

    /*@get:Rule(order = 3)
    val scenario = launchFragmentInContainer<ArtistFragment>(
        initialState = Lifecycle.State.INITIALIZED
    )*/

    @Inject
    lateinit var artistDao: ArtistDao

    @Inject
    lateinit var dataSource: SpotifyDataSource

    @Inject
    lateinit var okHttpClient: OkHttpClient

    val artistId = "7ltDVBr6mKbRvohxheJ9h1"

    // An Idling Resource that waits for Data Binding to have no pending bindings
    // private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        // Populate @Inject fields in test class
        // hiltRule.inject()
    }

    @Before
    fun setUp() {
        // scenario.moveToState(Lifecycle.State.RESUMED)
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("token_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artists_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("albums_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artists_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))
        // mockWebServerRule.server.enqueue(MockResponse().fromJson("albums_response.json"))

        mockWebServerRule.runHomeDispatcher()

        hiltRule.inject()

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        // IdlingRegistry.getInstance().register(dataBindingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))

        activityRule.scenario.onActivity { activity ->
            val fragment = ArtistFragment()
            val bun = Bundle()
            bun.putString("artistId", artistId)
            fragment.arguments = bun
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_splash_fragment, fragment, "ArtistFragment")
                .commitNowAllowingStateLoss()
        }
        // val scenario = launchFragmentInHiltContainer<ArtistFragment>(fragmentArgs = bundleOf(Pair("artistId", artistId)))
        // val scenario = launfragmentArgs = bundleOf(Pair("artistId", artistsId))chFragmentInContainer<ArtistFragment>(fragmentArgs = bundleOf(Pair("artistId", artistsId)))
        /*scenario.onFragment{
            IdlingRegistry.getInstance().register(resource)
        }*/
        /*val scenario = launchFragment<ArtistFragment>(
            themeResId = R.style.Theme_MusicAC
        ) {
            return@launchFragment ArtistFragment()
        }*/


        // mockWebServerRule.runArtistDispatcher()
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        // IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        IdlingRegistry.getInstance().unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @Test
    fun click_an_artist_album_navigates_to_album_detail() {

        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))

        // activityRule.scenario.recreate()

        /*onView(withId(R.id.recycler_artist))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))*/

        Thread.sleep(10000)

        onView(withId(R.id.top_albums_title)).check(
            ViewAssertions.matches(
                withText("Top Albums")
            )
        )

        /*onView(withId(R.id.artist_toolbar)).check(
            ViewAssertions.matches(
                ViewMatchers.hasDescendant(
                    ViewMatchers.withText("Bizarrap")
                )
            )
        )*/
    }

    /*@Test
    fun check_mock_server_is_working() = runTest {
        val artist = dataSource.getArtist(artistsId)
        artist.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("ROSALÍA", it.name)
        }
    }*/


}