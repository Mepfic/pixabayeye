package com.myapps.pixabayeye.details

import app.cash.turbine.test
import com.myapps.pixabayeye.details.model.StubModels
import com.myapps.pixabayeye.details.state.UiState
import com.myapps.pixabayeye.details.state.mapToDetailsState
import com.myapps.pixabayeye.details.ui.DetailsViewModel
import com.myapps.pixabayeye.domain.DetailsUseCase
import com.myapps.pixabayeye.test.common.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    private val detailsUseCase = mockk<DetailsUseCase>()
    private val detailsViewModel by lazy { DetailsViewModel(detailsUseCase) }

    private val testImageId: Long = 48894

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testSuccessState() {
        runTest {
            coEvery { detailsUseCase.invoke(testImageId) } returns StubModels.hitModel
            detailsViewModel.dataFlow.test {
                val initialState = awaitItem()
                assertEquals(UiState.DetailsState(), initialState)
                detailsViewModel.load(testImageId)
                val loadingState = awaitItem()
                assertTrue(loadingState.isLoading)
                val successState = awaitItem()
                assertEquals(StubModels.hitModel.let(mapToDetailsState), successState)
                assertFalse(successState.isLoading)
                cancelAndConsumeRemainingEvents().size.also { size ->
                    assertEquals(expected = 0, actual = size)
                }
            }
            coVerify(exactly = 1) { detailsUseCase.invoke(any()) }
        }
    }
}
