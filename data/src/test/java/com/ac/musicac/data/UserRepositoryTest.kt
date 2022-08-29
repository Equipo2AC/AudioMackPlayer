package com.ac.musicac.data

import arrow.core.left
import arrow.core.right
import com.ac.musicac.data.datasource.UserRemoteDataSource
import com.ac.musicac.data.repository.UserRepository
import com.ac.musicac.domain.Error
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @Mock
    lateinit var userRemoteDataSource : UserRemoteDataSource

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun `Call getUsers don't error`() = runBlocking {
        //Given
        val user = UserMock.fakeUser()
        whenever(userRemoteDataSource.getUser(any())).thenReturn(user.right())
        //When
        val result = userRepository.getUser("userID")

        //Then
        Assert.assertEquals(result, null)
    }

    @Test
    fun `Call getUsers error`() = runBlocking {
        //Given
        val error = Error.Unknown("")
        whenever(userRemoteDataSource.getUser(any())).thenReturn(error.left())
        //When
        val result = userRepository.getUser("userID")

        //Then
        Assert.assertEquals(result, error)
    }
}
