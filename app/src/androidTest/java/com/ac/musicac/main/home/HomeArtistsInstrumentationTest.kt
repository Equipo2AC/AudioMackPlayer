package com.ac.musicac.main.home

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.buildDatabaseArtist
import com.ac.musicac.data.database.dao.ArtistDao
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Inject
    lateinit var artistDao: ArtistDao

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(MockResponse().fromJson("artists_response.json"))
        hiltRule.inject()
        val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @After
    fun tearDown() {
        // Thread.sleep(3000)
    }

    /*@Test
    fun button_navigates_to_artist() = runTest {
        // onView(withId(R.id.btn)).perform(ViewActions.click())

        // Thread.sleep(3000)
        /// onView(withId(R.id.btn)).check(matches())
    }*/

    @Test
    fun check_4_IM_items_db() = runTest {
        artistDao.insertAllArtist(buildDatabaseArtist(1, 2, 3, 4))
        assertEquals(4, artistDao.artistCount())


    }

    @Test
    fun check_6_IM_items_db()  = runTest {
        artistDao.insertAllArtist(buildDatabaseArtist(5, 6, 7, 8, 9, 10))
        assertEquals(6, artistDao.artistCount())
    }

}