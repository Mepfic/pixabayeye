package com.myapps.pixabayeye.search.ui

import com.myapps.pixabayeye.common.BaseViewModel
import com.myapps.pixabayeye.domain.ImagesUseCase
import com.myapps.pixabayeye.domain.model.HitsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class SearchViewModel @Inject constructor(
     private val imagesUseCase: ImagesUseCase,
) : BaseViewModel() {

    private val _dataFlow = MutableSharedFlow<HitsModel>()
    val dataFlow: SharedFlow<HitsModel> = _dataFlow.asSharedFlow()

    init {
        getImages()
    }

    fun getImages(query: String = "fruits") {
        _dataFlow.launchInViewModelScope {
            imagesUseCase.invoke(query)
        }

    }
}