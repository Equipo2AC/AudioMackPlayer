package com.ac.musicac.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac.musicac.R
import com.ac.musicac.databinding.FragmentSearchBinding
import com.ac.musicac.domain.Type
import com.ac.musicac.ui.common.launchAndCollect
import com.ac.musicac.ui.main.OnChooseTypeChanged
import com.ac.musicac.ui.main.releases.ReleasesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search),
    SearchView.OnQueryTextListener, OnChooseTypeChanged {

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var searchState: SearchState

    private val adapter by lazy { SearchAdapter {  } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view).apply {
            onChooseTypeChanged = this@SearchFragment
        }
        searchState = buildSearchState()
        val searchView = binding.toolbar.menu.getItem(0).actionView as? SearchView
        searchView?.setOnQueryTextListener(this)

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            binding.recycler.adapter = adapter
            binding.loading = state.loading
            binding.query = state.query
            binding.items = state.search
            binding.type = state.type
        }
    }

    override fun onQueryTextSubmit(query: String) = false

    override fun onQueryTextChange(text: String) = viewModel.onQueryTextChange(text)

    override fun onChooseTypeChanged(type: Type?) {
        viewModel.onChangeType(type)
    }

}
