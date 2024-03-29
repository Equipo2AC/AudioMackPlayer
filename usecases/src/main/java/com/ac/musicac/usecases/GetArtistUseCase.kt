package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.PopularArtist
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(id: String): Either<Error?, PopularArtist> = repository.getArtist(id)
}