package com.example.myapplication.demo_API


import com.google.gson.annotations.SerializedName

data class PostResponce(@SerializedName("name")
              val name: String? = "",
              @SerializedName("id")
              val id: Int? = 0,
              @SerializedName("lang")
              val lang: String? = "")


