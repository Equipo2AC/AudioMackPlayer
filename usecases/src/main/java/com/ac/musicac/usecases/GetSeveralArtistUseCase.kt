package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import javax.inject.Inject

class GetSeveralArtistUseCase @Inject constructor(private val repository: MusicRepository) {

    operator fun invoke() = repository.savedArtists
}