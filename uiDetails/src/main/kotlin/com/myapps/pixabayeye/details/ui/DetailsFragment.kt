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
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.util.tagsToList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {
    private val viewModel: DetailsViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImages(args.imageId)
        viewModel.dataFlow.collectWithViewLifecycle { populateView(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateView(model: HitModel) {
        with(binding) {
            detailsImage.load(model.largeImageUrl) {
                crossfade(true)
                diskCachePolicy(CachePolicy.ENABLED)
                placeholder(R.drawable.ic_icon_placeholder)
                transformations(
                    RoundedCornersTransformation(
                        resources.getDimensionPixelSize(R.dimen.details_image_corner_radius)
                            .toFloat()
                    )
                )
            }

            nameText.text = getString(R.string.author_name_prefix, model.userName)
            likesText.text = model.likes.toString()
            downloadsText.text = model.downloads.toString()
            commentsText.text = model.comments.toString()
            binding.tagsChipGroup.removeAllViews()
            model.tags.tagsToList().forEach {
                Chip(requireContext()).apply {
                    text = it
                    binding.tagsChipGroup.addView(this)
                    isClickable = false
                }
            }
        }
    }
}
