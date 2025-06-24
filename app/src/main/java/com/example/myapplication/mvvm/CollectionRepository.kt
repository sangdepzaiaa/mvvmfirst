package com.example.myapplication.mvvm

// data/repository/CollectionRepository.kt
class CollectionRepository(private val api: UnsplashApiServicee) {
    suspend fun fetchCollections(page: Int, perPage: Int): List<CollectionItemResponse> {
        return api.getCollections(page, perPage)
    }
}
