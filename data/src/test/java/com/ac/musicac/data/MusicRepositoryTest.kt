package com.ac.musicac.data

import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.datasource.*
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.data.repository.UserRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.testshared.Mocks
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MusicRepositoryTest {

    @Mock
    lateinit var musicRemoteDataSource : MusicRemoteDataSource

    @Mock
    lateinit var locationDataSource: LocationDataSource

    @Mock
    lateinit var permissionChecker: PermissionChecker

    @Mock
    lateinit var artistLocalDataSource : ArtistLocalDataSource

    @Mock
    lateinit var albumLocalDataSource : AlbumLocalDataSource


    private lateinit var musicRepository: MusicRepository
    private lateinit var regionRepository: RegionRepository

    @Before
    fun setUp() {
        regionRepository = RegionRepository(locationDataSource, permissionChecker)
        musicRepository = MusicRepository(regionRepository, artistLocalDataSource, albumLocalDataSource, musicRemoteDataSource)
    }

    @Test
    fun `Call getReleases don't error`() = runBlocking {
        //Given
        val releases = Mocks.mockReleases()
        whenever(musicRemoteDataSource.getReleases(RegionRepository.DEFAULT_REGION)).thenReturn(releases.right())
        //When
        val result = musicRepository.getReleases()

        //Then
        Assert.assertEquals(result, releases.right())
    }

    @Test
    fun `Call getReleases error`() = runBlocking {
        //Given
        val error = Error.Unknown("")
        whenever(musicRemoteDataSource.getReleases(RegionRepository.DEFAULT_REGION)).thenReturn(error.left())
        //When
        val result = musicRepository.getReleases()

        //Then
        Assert.assertEquals(result, error.left())
    }
}
