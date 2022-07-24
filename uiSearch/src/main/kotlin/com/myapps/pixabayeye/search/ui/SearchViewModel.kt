package com.myapps.pixabayeye.search.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.myapps.pixabayeye.common.ui.BaseViewModel
import com.myapps.pixabayeye.domain.ImagesUseCase
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.search.state.mapToSearchItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
) : BaseViewModel() {

    private val query = MutableStateFlow(INIT_QUERY_VALUE)

    val dataFlow: StateFlow<PagingData<SearchItemState>> = query
        .flatMapLatest { query -> imagesUseCase.invoke(query).map { it.map(mapToSearchItemState) } }
        .cachedIn(viewModelScope)
        .catch { throwable ->
            emitError(ErrorState(throwable, throwable.message.orEmpty()))
        }
        .stateIn(viewModelScope, started = SharingStarted.Lazily, PagingData.empty())

    fun getImages(query: String) {
        this.query.tryEmit(query)
    }

    companion object {
        const val INIT_QUERY_VALUE = "fruits"
    }
}
