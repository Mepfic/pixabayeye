package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.test.common.stub.StubModels.hitModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

class DetailsUseCaseTest {

    private val detailsRepository = mockk<DetailsRepository>()
    private val detailsUseCase by lazy { DetailsUseCase(detailsRepository) }

    @ExperimentalCoroutinesApi
    @Test
    fun testUseCase() {
        runTest {
            coEvery { detailsRepository.getImageById(any()) } returns hitModel
            assertEquals(expected = hitModel, actual = detailsUseCase.invoke(hitModel.imageId))
        }
        coVerify(exactly = 1) { detailsRepository.getImageById(any()) }
    }
}
