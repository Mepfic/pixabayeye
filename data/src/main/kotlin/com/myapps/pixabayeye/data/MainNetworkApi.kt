package com.myapps.pixabayeye.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MainNetworkApi {

    @GET("api/")
    suspend fun getImages(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): ImagesResponse
}