package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.ReleasesRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Releases
import javax.inject.Inject

class GetReleasesUseCase @Inject constructor(private val releasesRepository: ReleasesRepository) {

    suspend operator fun invoke(): Either<Error?, Releases> = releasesRepository.getReleases()

}