package com.ac.musicac.usecases

import com.ac.musicac.data.repository.UserRepository
import com.ac.musicac.domain.Error
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: String): Error? = userRepository.getUser(userId)
}
