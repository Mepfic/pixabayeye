package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.test.common.stub.StubModels.hitModels
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ImagesUseCaseTest {

    private val imagesRepository = mockk<ImagesRepository>()
    private val imagesUseCase by lazy { ImagesUseCase(imagesRepository) }

    @ExperimentalCoroutinesApi
    @Test
    fun testUseCase() {
        runTest {
            coEvery { imagesRepository.getImages(any()) } returns flowOf(PagingData.from(hitModels))
            imagesUseCase.invoke("")
            coVerify(exactly = 1) { imagesRepository.getImages(any()) }
        }
    }
}
