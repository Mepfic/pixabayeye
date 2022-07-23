package com.myapps.pixabayeye.search

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import app.cash.turbine.test
import com.myapps.pixabayeye.domain.ImagesUseCase
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.search.ui.SearchViewModel
import com.myapps.pixabayeye.test.common.MainCoroutineRule
import com.myapps.pixabayeye.test.common.stub.StubModels.hitModels
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val imagesUseCase = mockk<ImagesUseCase>()
    private val searchViewModel by lazy { SearchViewModel(imagesUseCase) }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testSuccessState() {
        runTest {
            coEvery { imagesUseCase.invoke(any()) } returns flowOf(PagingData.from(hitModels))
            searchViewModel.dataFlow.test {
                val differ = AsyncPagingDataDiffer(
                    diffCallback = HitItemDiffCallback(),
                    updateCallback = NoopListCallback(),
                    mainDispatcher = StandardTestDispatcher(),
                    workerDispatcher = StandardTestDispatcher()
                )
                skipItems(1)
                differ.submitData(awaitItem())
                assertEquals(expected = hitModels, actual = differ.snapshot().items)
                cancelAndConsumeRemainingEvents().size.also { size ->
                    assertEquals(expected = 0, actual = size)
                }
            }
            coVerify(exactly = 1) { imagesUseCase.invoke(any()) }
        }
    }

    companion object {
        class HitItemDiffCallback : DiffUtil.ItemCallback<HitModel>() {
            override fun areItemsTheSame(oldItem: HitModel, newItem: HitModel) =
                oldItem.imageId == newItem.imageId

            override fun areContentsTheSame(oldItem: HitModel, newItem: HitModel) =
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
