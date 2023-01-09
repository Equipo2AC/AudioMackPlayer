package com.ac.musicac.main.home

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.R
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.di.qualifier.AlbumDummyIds
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
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

        Thread.sleep(2000)

        onView(ViewMatchers.withId(R.id.recycler_albums))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.release_detail_toolbar))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("MOTOMAMI"))))
    }

    /*@Test
    fun check_4_albums_items_db() = runTest {
        albumDao.insertAllAlbums(buildDatabaseAlbum(1, 2, 3, 4))
        Assert.assertEquals(4, albumDao.albumCount())
    }

    @Test
    fun check_6_albums_items_db()  = runTest {
        albumDao.insertAllAlbums(buildDatabaseAlbum(5, 6, 7, 8, 9, 10))
        Assert.assertEquals(6, albumDao.albumCount())
    }*/

    /*@Test
    fun check_mock_server_is_working() = runTest {
        val artists = dataSource.getSeveralAlbums(albumsIds)
        artists.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("3RQQmkQEvNCY4prGKE6oc5", it.albums[0].id)
        }
    }*/
}