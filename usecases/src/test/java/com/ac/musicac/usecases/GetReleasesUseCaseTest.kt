package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetReleasesUseCaseTest {

    @Mock
    lateinit var musicRepository: MusicRepository

    private lateinit var getReleasesUseCase: GetReleasesUseCase

    @Before
    fun setUp() {
        getReleasesUseCase = GetReleasesUseCase(musicRepository)
    }

    @Test
    fun `Invoke call getReleases`(): Unit = runBlocking {
        //When
        getReleasesUseCase()
        //Them
        verify(musicRepository).getReleases()
    }

}