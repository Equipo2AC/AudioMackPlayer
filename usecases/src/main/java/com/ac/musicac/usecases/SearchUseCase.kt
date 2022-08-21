package com.ac.musicac.usecases

import arrow.core.Either
import com.ac.musicac.data.repository.MusicRepository
import com.ac.musicac.domain.Error
import com.ac.musicac.domain.Search
import com.ac.musicac.domain.Type
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: MusicRepository) {

    suspend operator fun invoke(type: Type, query: String): Either<Error?, Search> =
        repository.findSearch(type, query)
}
