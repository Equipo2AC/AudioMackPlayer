package com.ac.musicac.main.artist

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
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

    /*@get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)*/

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

    val artistsId = "7ltDVBr6mKbRvohxheJ9h1"

    // An Idling Resource that waits for Data Binding to have no pending bindings
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        // Populate @Inject fields in test class
        // hiltRule.inject()
    }

    @Before
    fun setUp() {
        // scenario.moveToState(Lifecycle.State.RESUMED)

        mockWebServerRule.runHomeDispatcher()

        hiltRule.inject()

        // IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        // IdlingRegistry.getInstance().register(dataBindingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttpClient))

        val scenario = launchFragmentInHiltContainer<ArtistFragment>(fragmentArgs = bundleOf(Pair("artistId", artistsId)))
        // val scenario = launfragmentArgs = bundleOf(Pair("artistId", artistsId))chFragmentInContainer<ArtistFragment>(fragmentArgs = bundleOf(Pair("artistId", artistsId)))
        /*scenario.onFragment{
            IdlingRegistry.getInstance().register(resource)
        }*/
        /*val scenario = launchFragment<ArtistFragment>(
            themeResId = R.style.Theme_MusicAC
        ) {
            return@launchFragment ArtistFragment()
        }*/

        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))


        // mockWebServerRule.runArtistDispatcher()
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        IdlingRegistry.getInstance().unregister(OkHttp3IdlingResource.create("okHttp", okHttpClient))
    }

    @Test
    fun click_an_artist_album_navigates_to_album_detail() {

        // mockWebServerRule.server.enqueue(MockResponse().fromJson("artist_rosalia_response.json"))

        // scenario.recreate()
        Thread.sleep(2000)

        /*onView(withId(R.id.recycler_artist))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))*/

        Thread.sleep(5000)

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