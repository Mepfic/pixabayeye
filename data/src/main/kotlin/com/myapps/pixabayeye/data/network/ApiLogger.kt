package com.myapps.pixabayeye.data.network

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

internal class ApiLogger : HttpLoggingInterceptor.Logger {

    @Suppress("MagicNumber", "SwallowedException")
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                JSONObject(message).toString(4).also { print(it) }
            } catch (e: JSONException) {
                print(message)
            }
        } else {
            print(message)
        }
    }

    private fun print(message: String) {
        Timber.i(message)
        Log.d("API", message)
    }
}
