package com.myapps.pixabayeye.repository

import com.myapps.pixabayeye.domain.DetailsRepository
import com.myapps.pixabayeye.domain.model.HitModel
import com.myapps.pixabayeye.test.common.stub.StubModels
import javax.inject.Inject

class StubDetailsRepository @Inject constructor() : DetailsRepository {

    override suspend fun getImageById(id: Long): HitModel = StubModels.hitModel
}
