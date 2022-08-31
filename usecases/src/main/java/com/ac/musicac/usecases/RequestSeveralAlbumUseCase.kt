package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import javax.inject.Inject

class RequestSeveralAlbumUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(ids: String) = repository.getSeveralArtist(ids)
}