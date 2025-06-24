package com.example.myapplication.demo_API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface todoService {
    @GET("/todos/1")
    fun getTodo(): Call<TodoResponse>

    @GET("/todos/1")
    suspend fun getTodoSuspen() : TodoResponse

    @GET("/users")
    suspend fun getList() : List<User>

    @POST("/posts")
    suspend fun postusername(@Body request: PostRequest) : PostResponce


    companion object{
       fun retrofitLocator(retrofit: Retrofit): todoService{
           return retrofit.create(todoService::class.java)
       }
    }
}