package com.myapps.pixabayeye.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.myapps.pixabayeye.domain.ImagesUseCase
import com.myapps.pixabayeye.search.state.SearchItemState
import com.myapps.pixabayeye.search.state.mapToSearchItemState
import com.myapps.pixabayeye.search.ui.SearchViewModel
import com.myapps.pixabayeye.test.common.MainCoroutineRule
import com.myapps.pixabayeye.test.common.stub.StubModels.hitModels
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val imagesUseCase = mockk<ImagesUseCase>()
    private val searchViewModel by lazy { SearchViewModel(imagesUseCase) }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testSuccessState() {
        runTest {
            val pager = Pager(
                config = PagingConfig(pageSize = 1),
                pagingSourceFactory = hitModels.asPagingSourceFactory()
            )
            coEvery { imagesUseCase.invoke(any()) } returns pager.flow
            val actual = searchViewModel.dataFlow.asSnapshot()
            assertEquals(
                expected = hitModels.map(mapToSearchItemState),
                actual = actual
            )
            coVerify(exactly = 1) { imagesUseCase.invoke(any()) }
        }
    }

    companion object {
        class HitItemDiffCallback : DiffUtil.ItemCallback<SearchItemState>() {
            override fun areItemsTheSame(oldItem: SearchItemState, newItem: SearchItemState) =
                oldItem.imageId == newItem.imageId

            override fun areContentsTheSame(oldItem: SearchItemState, newItem: SearchItemState) =
                oldItem == newItem
        }

        class NoopListCallback : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) = Unit
            override fun onMoved(fromPosition: Int, toPosition: Int) = Unit
            override fun onInserted(position: Int, count: Int) = Unit
            override fun onRemoved(position: Int, count: Int) = Unit
        }
    }
}
