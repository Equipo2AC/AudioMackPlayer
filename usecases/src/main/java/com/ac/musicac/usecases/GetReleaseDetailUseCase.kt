package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Release
import javax.inject.Inject

class GetReleaseDetailUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(albumId: String): Either<Error?, Release> = repository.getReleaseDetail(albumId)

}