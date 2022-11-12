package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.test.common.stub.StubModels.hitModels
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

class ImagesUseCaseTest {

    private val repository = mockk<MainRepository>()
    private val imagesUseCase by lazy { ImagesUseCase(repository) }

    @ExperimentalCoroutinesApi
    @Test
    fun testUseCase() {
        runTest {
            coEvery { repository.getImages(any()) } returns flowOf(PagingData.from(hitModels))
            imagesUseCase.invoke("")
            coVerify(exactly = 1) { repository.getImages(any()) }
        }
    }
}
