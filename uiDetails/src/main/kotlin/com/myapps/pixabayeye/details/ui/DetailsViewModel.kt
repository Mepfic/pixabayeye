package com.myapps.pixabayeye.details.ui

import com.myapps.pixabayeye.common.ui.BaseViewModel
import com.myapps.pixabayeye.domain.DetailsUseCase
import com.myapps.pixabayeye.domain.model.HitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: DetailsUseCase
) : BaseViewModel() {

    private val _dataFlow = MutableSharedFlow<HitModel>(replay = 1)
    val dataFlow: SharedFlow<HitModel> = _dataFlow.asSharedFlow()

    fun getImages(id: Long) {
        _dataFlow.launchInViewModelScope {
            detailsUseCase.invoke(id)
        }
    }
}
