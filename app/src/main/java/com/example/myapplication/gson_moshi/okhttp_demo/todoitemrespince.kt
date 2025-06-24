package com.example.myapplication.gson_moshi.okhttp_demo


import com.squareup.moshi.Json

 data class todoitemrespince(
  @Json(name = "userId") val userId: Int,
  @Json(name = "id") val id: Int,
  @Json(name = "title") val title: String,
  @Json(name = "completed") val completed: Boolean
)