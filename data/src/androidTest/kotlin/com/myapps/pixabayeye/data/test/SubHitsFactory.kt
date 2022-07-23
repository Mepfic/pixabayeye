package com.myapps.pixabayeye.data.test

import com.myapps.pixabayeye.data.network.model.HitResponse
import com.myapps.pixabayeye.test.common.stub.StubModels.responseHitModel

object SubHitsFactory {
    fun createHits(perPage: Int): List<HitResponse> =
        mutableListOf<HitResponse>().apply {
            for (i in 1..perPage) {
                add(responseHitModel)
            }
        }
}
