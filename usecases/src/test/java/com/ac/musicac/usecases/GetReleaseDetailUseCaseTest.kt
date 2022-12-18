package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetReleaseDetailUseCaseTest {

    @Mock
    lateinit var musicRepository: MusicRepository

    private lateinit var getReleaseDetailUseCase: GetReleaseDetailUseCase

    @Before
    fun setUp() {
        getReleaseDetailUseCase = GetReleaseDetailUseCase(musicRepository)
    }

    @Test
    fun `Invoke call getReleaseDetail`(): Unit = runBlocking {
        //Given
        val albumId = "albumId"
        //When
        getReleaseDetailUseCase(albumId)
        //Them
        verify(musicRepository).getReleaseDetail(albumId)
    }

}