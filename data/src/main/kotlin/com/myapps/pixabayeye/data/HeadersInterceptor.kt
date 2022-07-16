package com.myapps.pixabayeye.data

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.headersContentLength

/**
 * For intercept request & response to enrich them other things
 */
internal class HeadersInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request().newBuilder().build())
        val headers = response.headers
        val length = response.headersContentLength()
        return response
    }
}