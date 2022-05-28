package com.ac.musicac.ui.main.releases.detail.di

import androidx.lifecycle.SavedStateHandle
import com.ac.musicac.di.AlbumId
import com.ac.musicac.ui.main.releases.detail.ReleaseDetailFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ReleaseDetailViewModelModule {

    @Provides
    @ViewModelScoped
    @AlbumId
    fun provideMovieId(savedStateHandle: SavedStateHandle) =
        ReleaseDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).albumId

}