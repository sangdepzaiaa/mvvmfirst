package com.example.myapplication.demo_API

import com.google.gson.annotations.SerializedName

data class TodoResponse(@SerializedName("id")
                        val id: Int = 0,
                        @SerializedName("completed")
                        val completed: Boolean = false,
                        @SerializedName("title")
                        val title: String = "",
                        @SerializedName("userId")
                        val userId: Int = 0)