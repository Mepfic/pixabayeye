package com.myapps.pixabayeye.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    protected fun <T> Flow<T>.collectWithViewLifecycle(block: suspend (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { block(it) }
        }
    }
}