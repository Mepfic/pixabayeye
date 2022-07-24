package com.myapps.pixabayeye.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.google.android.material.chip.Chip
import com.myapps.pixabayeye.common.R
import com.myapps.pixabayeye.common.ui.BaseFragment
import com.myapps.pixabayeye.details.databinding.FragmentDetailsBinding
import com.myapps.pixabayeye.details.state.DetailsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImages(args.imageId)
        viewModel.dataFlow.collectWithViewLifecycle { populateView(it) }
        viewModel.error.collectWithViewLifecycle { binding.root.showSnackError(it.message) }
    }

    private fun populateView(state: DetailsState) {
        with(binding) {
            detailsImage.load(state.largeImageUrl) {
                crossfade(true)
                diskCachePolicy(CachePolicy.ENABLED)
                placeholder(R.drawable.ic_icon_placeholder)
                error(R.drawable.ic_icon_placeholder)
                transformations(
                    RoundedCornersTransformation(
                        resources.getDimensionPixelSize(R.dimen.details_image_corner_radius)
                            .toFloat()
                    )
                )
            }
            nameText.text = getString(R.string.author_name_prefix, state.userName)
            likesText.text = state.likes.toString()
            downloadsText.text = state.downloads.toString()
            commentsText.text = state.comments.toString()
            binding.tagsChipGroup.removeAllViews()
            state.tags.forEach {
                Chip(requireContext()).apply {
                    text = it
                    binding.tagsChipGroup.addView(this)
                    isClickable = false
                }
            }
        }
    }
}
