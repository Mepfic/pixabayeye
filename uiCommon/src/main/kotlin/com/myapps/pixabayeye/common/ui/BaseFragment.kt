package com.myapps.pixabayeye.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.myapps.pixabayeye.common.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    protected fun <T> Flow<T>.collectWithViewLifecycle(block: suspend (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { block(it) }
        }
    }

    protected fun View.hideKeyboard() {
        val imm = ContextCompat.getSystemService(
            context,
            InputMethodManager::class.java
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    protected fun View.showSnackError(message: String) {
        val errorText = message.ifEmpty { getString(R.string.general_error_message) }
        Snackbar.make(this, errorText, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
