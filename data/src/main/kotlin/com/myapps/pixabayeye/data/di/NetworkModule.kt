package com.myapps.pixabayeye.data.di

import com.myapps.pixabayeye.data.network.ApiLogger
import com.myapps.pixabayeye.data.network.EndPoints
import com.myapps.pixabayeye.data.network.MainNetworkApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkApi(
        okHttpClient: OkHttpClient,
        converterFactory: MoshiConverterFactory,
    ): MainNetworkApi =
        Retrofit.Builder()
            .baseUrl(EndPoints.PIXABAY_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(MainNetworkApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(ApiLogger()).setLevel(HttpLoggingInterceptor.Level.BODY)

    private const val CONNECT_TIMEOUT = 20L
    private const val READ_WRITE_TIMEOUT = 20L
}
