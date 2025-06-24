package com.example.myapplication.mvvm

data class CollectionItemResponse(
    val id: String,
    val title: String,
    val description: String?,
    val cover_photo: CoverPhoto
)

data class CoverPhoto(
    val urls: Urls
)

data class Urls(
    val regular: String
)
