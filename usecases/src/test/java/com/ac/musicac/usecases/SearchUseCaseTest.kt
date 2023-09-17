package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Type
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {

    @Mock
    lateinit var musicRepository: MusicRepository

    private lateinit var searchUseCase: SearchUseCase

    @Before
    fun setUp() {
        searchUseCase = SearchUseCase(musicRepository)
    }

    @Test
    fun `Invoke call findSearch`(): Unit = runBlocking {
        //Given
        val query = "text"
        val type = Type.ALBUM
        //When
        searchUseCase(type, query)
        //Them
        verify(musicRepository).findSearch(type, query)
    }

}
