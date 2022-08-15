package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Artist
import com.ac.musicac.domain.Error
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(private val artistRepository: MusicRepository) {

    suspend operator fun invoke(id: String): Either<Error?, Artist> = artistRepository.getArtist(id)
}