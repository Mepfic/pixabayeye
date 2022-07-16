package com.myapps.pixabayeye.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val error = SingleFlowEvent<ErrorState>()

    /** MutableLiveData analog */
    val sharedFlow = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun <T> Flow<T>.convertToSharedFlow(): SharedFlow<T> =
        shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    protected fun <T : Any> MutableSharedFlow<T>.launchInViewModelScope(
        errorMessage: String? = null,
        errorAction: (() -> Unit)? = null,
        block: suspend () -> T
    ) {
        viewModelScope.launch {
            runCatching {
                _isLoading.emit(true)
                block.invoke()
            }
                .onSuccess { emit(it) }
                .onFailure {
                    Timber.e(it)
                    error.emit(
                        ErrorState(it, errorMessage ?: it.localizedMessage ?: "", errorAction)
                    )
                }
            _isLoading.emit(false)
        }
    }

    data class ErrorState(
        val throwable: Throwable,
        val message: String,
        val action: (() -> Unit)? = null
    )
}