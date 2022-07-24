package com.myapps.pixabayeye.details.ui

import com.myapps.pixabayeye.common.ui.BaseViewModel
import com.myapps.pixabayeye.details.state.DetailsState
import com.myapps.pixabayeye.details.state.mapToDetailsState
import com.myapps.pixabayeye.domain.DetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase
) : BaseViewModel() {

    private val _dataFlow = MutableSharedFlow<DetailsState>(replay = 1)
    val dataFlow: SharedFlow<DetailsState> = _dataFlow.asSharedFlow()

    fun getImages(id: Long) {
        _dataFlow.launchInViewModelScope {
            detailsUseCase.invoke(id).let(mapToDetailsState)
        }
    }
}
