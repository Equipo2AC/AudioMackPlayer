package com.ac.musicac.usecases

import com.ac.musicac.data.repository.AuthenticationRepository
import com.ac.musicac.domain.Error
import javax.inject.Inject

class RequestAuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(): Error? = authenticationRepository.requestAuthentication()
}
