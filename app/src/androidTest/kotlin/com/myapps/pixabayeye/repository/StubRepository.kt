package com.myapps.pixabayeye.repository

import androidx.paging.PagingData
import com.myapps.pixabayeye.domain.MainRepository
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.test.common.stub.StubModels
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StubRepository @Inject constructor() : MainRepository {
    override fun getImages(query: String): Flow<PagingData<HitModel>> =
        flowOf(PagingData.from(StubModels.hitModels))

    override suspend fun getImageById(id: Long): HitModel = StubModels.hitModel
}
