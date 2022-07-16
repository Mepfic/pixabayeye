package com.myapps.pixabayeye.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.myapps.pixabayeye.common.BaseFragment
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.search.adapter.ImageAdapter
import com.myapps.pixabayeye.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        adapter = ImageAdapter {
            val route = getString(R.string.route_details_fragment_request, it)
            val request = NavDeepLinkRequest.Builder
                .fromUri(route.toUri())
                .build()

            findNavController().navigate(request)
        }
        binding.recycler.adapter = adapter

        viewModel.dataFlow.collectWithViewLifecycle {
            adapter.submitList(it.hits)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}