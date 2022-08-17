package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.ReleasesRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.releases.Release
import javax.inject.Inject

class GetReleaseDetailUseCase @Inject constructor(private val releasesRepository: ReleasesRepository) {

    suspend operator fun invoke(albumId: String): Either<Error?, Release> = releasesRepository.getReleaseDetail(albumId)

}