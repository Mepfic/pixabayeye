package com.myapps.pixabayeye.data.cache

import com.myapps.pixabayeye.data.HitResponse

class MemoryCache {

    private val cache = mutableMapOf<Long, HitResponse>()

    fun updateCache(hits: List<HitResponse>) {
        hits.forEach { cache[it.imageId] = it }
    }

    fun getHitFromCache(id: Long): HitResponse? = cache[id]
}