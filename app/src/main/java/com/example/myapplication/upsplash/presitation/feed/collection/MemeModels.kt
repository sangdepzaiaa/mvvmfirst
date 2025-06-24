package com.example.myapplication.upsplash.presitation.feed.collection

import com.squareup.moshi.Json


data class MemeResponseWrapper(
    val success: Boolean,
    val data: MemeData
)

data class MemeData(
    val memes: List<MemeItem>
)

data class MemeItem(
    val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    @Json(name = "box_count") val boxCount: Int
)