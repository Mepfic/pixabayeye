package com.myapps.pixabayeye.domain.model

data class HitsModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<HitModel>
)
