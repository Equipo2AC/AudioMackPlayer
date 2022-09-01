package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Error
import javax.inject.Inject

class RequestSeveralArtistUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(ids: String): Error? = repository.getSeveralArtist(ids)
}