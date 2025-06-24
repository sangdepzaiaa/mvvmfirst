package com.example.myapplication.demo_API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object servicelocator {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val todoServic by lazy {
        todoService.retrofitLocator(retrofit)
    }
}