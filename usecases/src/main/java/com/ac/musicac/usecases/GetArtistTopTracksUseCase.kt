package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.ArtistTopTrack
import com.ac.musicac.domain.Error
import javax.inject.Inject

class GetArtistTopTracksUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(id: String): Either<Error?, ArtistTopTrack> = repository.getArtistTopTracks(id)
}