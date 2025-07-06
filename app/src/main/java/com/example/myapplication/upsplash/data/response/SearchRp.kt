package com.example.myapplication.upsplash.data.response

import com.squareup.moshi.Json

class SearchRp (
    @Json(name = "total") val total:Int,
    @Json(name = "total_pages") val totalpages: Int,
    @Json(name = "results") val resultss: List<CollectionsItemRp.CoverPhoto>,
)