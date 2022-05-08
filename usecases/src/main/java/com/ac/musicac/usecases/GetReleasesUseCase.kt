package com.ac.musicac.usecases

import com.ac.musicac.data.repository.ReleasesRepository
import com.ac.musicac.domain.Error
import javax.inject.Inject

class GetReleasesUseCase @Inject constructor(private val releasesRepository: ReleasesRepository) {

    suspend operator fun invoke(): Error? {
        return releasesRepository.getReleases()
    }
}