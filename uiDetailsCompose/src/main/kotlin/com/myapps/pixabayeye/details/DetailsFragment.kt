package com.myapps.pixabayeye.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.myapps.pixabayeye.details.theme.PixabayTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalLifecycleComposeApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PixabayTheme {
                    DetailsRoute(viewModel = viewModel)
                }
            }
        }
    }
}
