package com.ac.musicac.main.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.server.EspressoIdlingResource
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.di.qualifier.ArtistDummyIds
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

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
    var activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    @ArtistDummyIds
    lateinit var artistsIds: String

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.runDispatcher()
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))
        // activityRule.scenario.recreate()
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
        // mockWebServerRule.server.shutdown()
    }

    @Test
    fun app_shows_several_artists() {

        onView(withId(R.id.recycler_artist))
            .check(matches(hasDescendant(withText("Bizarrap"))))
    }

    @Test
    fun click_in_rosalia_artist_navigates_to_detail() {

        onView(withId(R.id.recycler_artist))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.artist_toolbar))
            .check(matches(hasDescendant(withText("ROSALÍA"))))
    }

    @Test
    fun click_in_bizarrap_artist_navigates_to_detail() {

        onView(withId(R.id.recycler_artist))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        onView(withId(R.id.artist_toolbar))
            .check(matches(hasDescendant(withText("Bizarrap"))))
    }

    @Test
    fun click_in_badbunny_artist_navigates_to_detail() {

        onView(withId(R.id.recycler_artist))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))



        onView(withId(R.id.artist_toolbar))
            .check(matches(hasDescendant(withText("Bad Bunny"))))
    }
}