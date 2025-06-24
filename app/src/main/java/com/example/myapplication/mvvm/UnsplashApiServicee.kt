package com.example.myapplication.mvvm

import retrofit2.http.GET
import retrofit2.http.Query

// data/api/UnsplashApiService.kt
interface UnsplashApiServicee {
    @GET("collections")
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CollectionItemResponse>
}


