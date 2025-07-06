package com.example.myapplication.upsplash.data.intercepter

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class AuthorizationInterceptor(var clientId : String): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newrequest = request.newBuilder()
            .addHeader(
                name = "Authorization",
                value = "Client-ID $clientId"
            )
            .build()
        val response = chain.proceed(newrequest)
        return response
    }


}