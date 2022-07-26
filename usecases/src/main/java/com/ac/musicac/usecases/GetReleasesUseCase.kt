package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Releases
import javax.inject.Inject

class GetReleasesUseCase @Inject constructor(private val releasesRepository: MusicRepository) {

    suspend operator fun invoke(): Either<Error?, Releases> = releasesRepository.getReleases()

}
