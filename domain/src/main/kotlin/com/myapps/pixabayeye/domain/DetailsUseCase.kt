package com.myapps.pixabayeye.domain

import com.myapps.pixabayeye.domain.model.HitModel
import javax.inject.Inject

class DetailsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(id: Long): HitModel =
        repository.getImageById(id)
}
