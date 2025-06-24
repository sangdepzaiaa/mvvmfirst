package com.example.myapplication.gson_moshi

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

internal data class student(
    @SerializedName("id")
    @Json(name = "id") val id: String,
    @SerializedName("first_name")
    @Json(name = "first_name") val firstName: String,
    @SerializedName("last_name")
    @Json(name = "last_name") val lastName: String,
    @SerializedName("age")
    @Json(name = "age") val age: Int
)