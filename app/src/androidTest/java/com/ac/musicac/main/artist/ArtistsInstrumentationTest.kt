package com.ac.musicac.main.artist

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.server.*
import com.ac.musicac.ui.main.artist.ArtistFragment
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
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

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    // An Idling Resource that waits for Data Binding to have no pending bindings
    // private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun setUp() {

        mockWebServerRule.runDispatcher()

        hiltRule.inject()

        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))

        activityRule.scenario.onActivity { activity ->
            val artistFragment = ArtistFragment()
            val bun = Bundle()
            bun.putString("artistId", "7ltDVBr6mKbRvohxheJ9h1")
            artistFragment.arguments = bun
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_splash_fragment, artistFragment, "ArtistFragment")
                .commitNowAllowingStateLoss()
        }
        // val scenario = launchFragmentInHiltContainer<ArtistFragment>(fragmentArgs = bundleOf(Pair("artistId", artistId)))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @Test
    fun artist_fragment_shows_an_artist() {

        Thread.sleep(5000)

        onView(withId(R.id.artist_toolbar)).check(
            matches(hasDescendant(withText("ROSALÍA")))
        )

        onView(withId(R.id.top_albums_title)).check(
            matches(withText("Top Albums"))
        )
    }

    @Test
    fun artist_fragment_shows_an_artist_albums() {

        Thread.sleep(5000)

        onView(withId(R.id.recycler_artist_albums)).check(
            matches(hasDescendant(withText("DESPECHÁ RMX")))
        )
    }

    @Test
    fun artist_fragment_shows_an_album_when_clicked() {

        Thread.sleep(5000)

        /*onView(withId(R.id.recycler_artist_albums))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))*/

        onView(withId(R.id.recycler_artist_albums)).check(
            matches(hasDescendant(withText("MOTOMAMI +")))
        )
    }

}