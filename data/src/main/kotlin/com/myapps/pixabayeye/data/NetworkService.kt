package com.myapps.pixabayeye.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService {

    companion object {

        fun build(baseUrl: String): MainNetworkApi {
            return Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .addInterceptor(HeadersInterceptor())
                        .build()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory())
                            .build()
                    )
                )
                .build()
                .create(MainNetworkApi::class.java)
        }
    }
}