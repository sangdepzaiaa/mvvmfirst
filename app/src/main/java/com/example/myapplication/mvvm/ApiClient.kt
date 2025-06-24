package com.example.myapplication.mvvm

import com.example.myapplication.upsplash.data.UnsplashApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// data/api/ApiClient.kt
object ApiClient {
    private val moshi = Moshi.Builder().build()

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    val unsplashApi: UnsplashApiServicee by lazy {
        retrofit.create(UnsplashApiServicee::class.java)
    }
}
