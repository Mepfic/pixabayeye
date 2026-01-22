package com.myapps.pixabayeye.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapps.pixabayeye.details.state.UiState
import com.myapps.pixabayeye.details.state.mapToDetailsState
import com.myapps.pixabayeye.domain.DetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase,
) : ViewModel() {

    private val _dataFlow = MutableStateFlow(UiState.DetailsState())
    val dataFlow: StateFlow<UiState.DetailsState> = _dataFlow.asStateFlow()

    fun load(imageId: Long) {
        getImages(imageId)
    }

    private fun getImages(id: Long) {
        viewModelScope.launch {
            _dataFlow.update { it.copy(isLoading = true) }
            runCatching {
                detailsUseCase.invoke(id).let(mapToDetailsState)
            }
                .onSuccess { result -> _dataFlow.emit(result) }
                .onFailure { error ->
                    Timber.e(error)
                    _dataFlow.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }
}
