package com.ac.musicac.data

import com.ac.musicac.data.PermissionChecker.Permission
import com.ac.musicac.data.datasource.LocationDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RegionRepositoryTest {

    @Mock
    lateinit var locationDataSource: LocationDataSource

    @Mock
    lateinit var permissionChecker: PermissionChecker

    private lateinit var regionRepository: RegionRepository

    @Before
    fun setUp() {
        regionRepository = RegionRepository(locationDataSource, permissionChecker)
    }

    @Test
    fun `returns default when coarse permission not granted`() = runBlocking {
        //Given
        whenever(permissionChecker.check(Permission.COARSE_LOCATION)).thenReturn(false)
        //When
        val region = regionRepository.findLastRegion()
        //Then
        assertEquals(RegionRepository.DEFAULT_REGION, region)
    }

    @Test
    fun `returns region from location data source when permission granted`() = runBlocking {
        //Given
        whenever(permissionChecker.check(Permission.COARSE_LOCATION)).thenReturn(true)
        whenever(locationDataSource.findLastRegion()).thenReturn("ES")
        //When
        val region = regionRepository.findLastRegion()
        //Then
        assertEquals("ES", region)
    }
}