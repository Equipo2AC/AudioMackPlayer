package com.ac.musicac.main.search

import android.view.inputmethod.EditorInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.server.EspressoIdlingResource
import com.ac.musicac.data.server.MockWebServerTokenRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.ui.main.search.SearchFragment
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
class SearchArtistsInstrumentationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerTokenRule()

    @get:Rule(order = 2)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.runDispatcher()
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))

        activityRule.scenario.onActivity { activity ->
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_splash_fragment, SearchFragment(), "TEST SearchFragment")
                .commitNowAllowingStateLoss()
        }
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @Test
    fun search_fragment_shows_an_artist_when_searched() {

        onView(withId(R.id.search_option))
            .perform(click())

        Thread.sleep(3000)

        onView(ViewMatchers.hasImeAction(EditorInfo.IME_ACTION_SEARCH))
            .perform(ViewActions.typeText("Rosa"))

        Thread.sleep(3000)

        onView(withText("Artistas"))
            .perform(click())

        Thread.sleep(3000)

        onView(withId(R.id.recycler_search)).check(
            matches(hasDescendant(withText("Rosario")))
        )
    }

}