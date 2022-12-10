package com.ac.musicac.main.home

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.ac.musicac.buildDatabaseAlbum
import com.ac.musicac.data.database.dao.AlbumDao
import com.ac.musicac.data.server.MockWebServerRule
import com.ac.musicac.data.server.OkHttp3IdlingResource
import com.ac.musicac.data.server.datasource.SpotifyDataSource
import com.ac.musicac.data.server.fromJson
import com.ac.musicac.di.qualifier.AlbumDummyIds
import com.ac.musicac.ui.navHostActivity.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.*
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
    lateinit var albumDao: AlbumDao

    @Inject
    lateinit var dataSource: SpotifyDataSource

    @Inject
    @AlbumDummyIds
    lateinit var albumsIds: String

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(MockResponse().fromJson("albums_response.json"))
        hiltRule.inject()
        val resource = OkHttp3IdlingResource.create("okHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val artists = dataSource.getSeveralAlbums(albumsIds)
        artists.fold({ throw Exception(it.toString()) }) {
            Assert.assertEquals("3RQQmkQEvNCY4prGKE6oc5", it.albums[0].id)
        }
    }

    @Test
    fun check_4_IM_items_db() = runTest {
        albumDao.insertAllAlbums(buildDatabaseAlbum(1, 2, 3, 4))
        Assert.assertEquals(4, albumDao.albumCount())


    }

    @Test
    fun check_6_IM_items_db()  = runTest {
        albumDao.insertAllAlbums(buildDatabaseAlbum(5, 6, 7, 8, 9, 10))
        Assert.assertEquals(6, albumDao.albumCount())
    }
}