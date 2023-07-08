package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.domain.model.HitModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesUseCase @Inject constructor(
    private val imageRepository: ImagesRepository,
) {
    operator fun invoke(query: String): Flow<PagingData<HitModel>> =
        imageRepository.getImages(query)
}
