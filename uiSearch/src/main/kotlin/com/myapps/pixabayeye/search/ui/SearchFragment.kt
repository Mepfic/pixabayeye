package com.myapps.pixabayeye.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.myapps.pixabayeye.common.BaseFragment
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.search.adapter.ImageAdapter
import com.myapps.pixabayeye.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment() {
    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageAdapter { navigateToDetails(it) }
        binding.recycler.adapter = adapter
        viewModel.dataFlow.collectWithViewLifecycle {
            adapter.submitData(it)
        }
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        with(binding) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.getImages(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
            searchView.setOnClickListener { searchView.isIconified = false }

            adapter.loadStateFlow.collectWithViewLifecycle {
                progressContainer.isVisible = it.refresh is LoadState.Loading
            }
            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun navigateToDetails(itemId: Long) {
        val route = getString(R.string.route_details_fragment_request, itemId)
        val request = NavDeepLinkRequest.Builder
            .fromUri(route.toUri())
            .build()
        findNavController().navigate(request)
    }
}