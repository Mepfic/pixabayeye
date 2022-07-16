package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.domain.model.HitsModel
import com.myapps.pixabayeye.domain.model.mapToHitsModel
import javax.inject.Inject

class ImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(query: String): HitsModel =
        imageRepository.getImages(query).let(mapToHitsModel)
}