package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Albums
import com.ac.musicac.domain.Error
import javax.inject.Inject

class GetArtistAlbumsUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(id: String): Either<Error?, Albums> = repository.getArtistAlbums(id)
}