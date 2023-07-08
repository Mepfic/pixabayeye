package com.myapps.pixabayeye.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableSharedFlow<ErrorState>()
    val error: SharedFlow<ErrorState> = _error.asSharedFlow()

    protected fun <T : Any> MutableSharedFlow<T>.launchInViewModelScope(
        errorMessage: String? = null,
        errorAction: (() -> Unit)? = null,
        block: suspend () -> T,
    ) {
        viewModelScope.launch {
            runCatching {
                _isLoading.emit(true)
                block.invoke()
            }
                .onSuccess { emit(it) }
                .onFailure {
                    Timber.e(it)
                    emitError(ErrorState(it, errorMessage.orEmpty(), errorAction))
                }
            _isLoading.emit(false)
        }
    }

    protected suspend fun emitError(state: ErrorState) {
        _error.emit(state)
    }

    data class ErrorState(
        val throwable: Throwable,
        val message: String,
        val action: (() -> Unit)? = null,
    )
}
