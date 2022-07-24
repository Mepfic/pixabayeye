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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.common.ui.BaseFragment
import com.myapps.pixabayeye.search.adapter.ImageAdapter
import com.myapps.pixabayeye.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: ImageAdapter

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageAdapter({ showTransitionDialog(it) }, { viewModel.getImages(it) })
        binding.recycler.adapter = adapter
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.getImages(it) }
                    binding.root.hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
            searchView.setOnClickListener { searchView.isIconified = false }

            viewModel.dataFlow.collectWithViewLifecycle { adapter.submitData(it) }

            viewModel.error.collectWithViewLifecycle { root.showSnackError(it.message) }

            adapter.loadStateFlow.collectWithViewLifecycle {
                progressContainer.isVisible = it.refresh is LoadState.Loading
                emptyText.isVisible = it.append is LoadState.NotLoading && adapter.itemCount < 1
                (it.refresh as? LoadState.Error)?.let { errorState ->
                    root.showSnackError(errorState.error.message.orEmpty())
                }
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

    private fun showTransitionDialog(itemId: Long) {
        MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialogTheme).apply {
            setCancelable(true)
            setTitle(R.string.dialog_message)
            setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                dialog.cancel()
            }
            setPositiveButton(R.string.dialog_positive_button) { _, _ ->
                navigateToDetails(itemId)
            }
        }.show()
    }
}
