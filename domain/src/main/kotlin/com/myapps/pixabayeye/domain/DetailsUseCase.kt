package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.domain.model.mapToHitModel
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(id: Long): HitModel =
        imageRepository.getImageById(id).let(mapToHitModel)
}