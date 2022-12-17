package com.ac.musicac.ui.main.release

import CoroutinesTestRule
import com.ac.musicac.testshared.Mocks
import com.ac.musicac.ui.main.artist.ArtistViewModel
import com.ac.musicac.ui.main.releases.detail.ReleaseDetailViewModel
import com.ac.musicac.usecases.GetReleaseDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReleaseDetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getReleaseDetailUseCase: GetReleaseDetailUseCase

    private lateinit var  vm: ReleaseDetailViewModel

    private val albumId = "7ltDVBr6mKbRvohxheJ9h1"
    private val albumSample = Mocks.mockAlbums()


    @Before
    fun setUp() {
        vm = ReleaseDetailViewModel(albumId, getReleaseDetailUseCase)
    }
}