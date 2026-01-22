package com.myapps.pixabayeye.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.myapps.pixabayeye.domain.ImagesUseCase
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.search.state.mapToSearchItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
) : ViewModel() {

    private val query = MutableStateFlow(INIT_QUERY_VALUE)

    @OptIn(ExperimentalCoroutinesApi::class)
    val dataFlow: StateFlow<PagingData<SearchItemState>> = query
        .flatMapLatest { query ->
            imagesUseCase.invoke(query).map { it.map(mapToSearchItemState) }
        }
        .cachedIn(viewModelScope)
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(SUBSCRIBE_TIMEOUT),
            PagingData.empty()
        )

    fun getImages(query: String) {
        this.query.value = query
    }

    companion object {
        const val INIT_QUERY_VALUE = "fruits"
        const val SUBSCRIBE_TIMEOUT = 5_000L
    }
}
