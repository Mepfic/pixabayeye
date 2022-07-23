package com.myapps.pixabayeye.domain

import androidx.paging.PagingData
import com.myapps.pixabayeye.domain.model.HitModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ImagesUseCase @Inject constructor(
    private val imageRepository: ImagesRepository
) {
    operator fun invoke(query: String): Flow<PagingData<HitModel>> =
        imageRepository.getImages(query)
}
