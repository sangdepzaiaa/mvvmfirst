package com.example.myapplication.gson_moshi.okhttp_demo

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class customHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println(">>> CustomHeaderInterceptor START")

        val request = chain.request()
        val newrequest = request.newBuilder()
            .addHeader(
                name = "android7",
                value = "android${Build.VERSION.SDK_INT}/${Build.MODEL}/${Build.DEVICE}"
            )
            .build()
        val responce = chain.proceed(newrequest)
        println("<<< CustomHeaderInterceptor END")
        return  responce
    }
}