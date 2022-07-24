package com.myapps.pixabayeye.details

import app.cash.turbine.test
import com.myapps.pixabayeye.details.state.mapToDetailsState
import com.myapps.pixabayeye.details.ui.DetailsViewModel
import com.myapps.pixabayeye.domain.DetailsUseCase
import com.myapps.pixabayeye.test.common.MainCoroutineRule
import com.myapps.pixabayeye.test.common.stub.StubModels
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule

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
                detailsViewModel.getImages(testImageId)
                assertEquals(
                    expected = StubModels.hitModel.let(mapToDetailsState),
                    actual = awaitItem()
                )
                cancelAndConsumeRemainingEvents().size.also { size ->
                    assertEquals(expected = 0, actual = size)
                }
            }
            coVerify(exactly = 1) { detailsUseCase.invoke(any()) }
        }
    }
}
