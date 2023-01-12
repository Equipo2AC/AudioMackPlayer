package com.ac.musicac.main.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.di.qualifier.AlbumDummyIds
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeAlbumsInstrumentationTest {

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
    @AlbumDummyIds
    lateinit var albumsIds: String

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.runDispatcher()
        hiltRule.inject()
        val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun click_an_album_navigates_to_detail() {

        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.recycler_albums))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))



        onView(ViewMatchers.withId(R.id.release_detail_toolbar))
            .check(matches(hasDescendant(withText("MOTOMAMI"))))
    }

    @Test
    fun click_an_artist_navigates_to_detail() {

        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.recycler_artist))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))



        onView(ViewMatchers.withId(R.id.artist_toolbar))
            .check(matches(hasDescendant(withText("ROSALÍA"))))
    }

    @Test
    fun app_shows_several_artists() {

        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.recycler_artist))
            .check(matches(hasDescendant(withText("Bizarrap"))))
    }

    @Test
    fun app_shows_several_albums() {

        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.recycler_albums))
            .check(matches(hasDescendant(withText("MOTOMAMI"))))

    }
}