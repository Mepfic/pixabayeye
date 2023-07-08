package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.domain.model.HitModel
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository,
) {

    suspend operator fun invoke(id: Long): HitModel =
        detailsRepository.getImageById(id)
}
