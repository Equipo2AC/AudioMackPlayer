package com.ac.musicac.usecases

import com.ac.musicac.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

    @Mock
    lateinit var userRepository: UserRepository

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        getUserUseCase = GetUserUseCase(userRepository)
    }

    @Test
    fun `Invoke call findSearch`(): Unit = runBlocking {
        //Given
        val userId = "userId"
        //When
        getUserUseCase(userId)
        //Them
        verify(userRepository).getUser(userId)
    }

}
