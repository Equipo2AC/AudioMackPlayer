package com.ac.musicac.usecases

import com.ac.musicac.data.repository.MusicRepository
import javax.inject.Inject

class GetSeveralAlbumUseCase @Inject constructor(private val repository: MusicRepository) {

    operator fun invoke() = repository.savedAlbums
}