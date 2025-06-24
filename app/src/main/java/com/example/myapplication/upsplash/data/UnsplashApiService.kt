package com.example.myapplication.upsplash.data

import com.example.myapplication.upsplash.data.response.CollectionItemResponce

import com.example.myapplication.upsplash.data.response.CollectionItemResponse
import com.example.myapplication.upsplash.data.response.CollectionsItemRp
import com.example.myapplication.upsplash.presitation.feed.collection.MemeResponseWrapper
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("collections")
     suspend fun getCollections(
         @Query("page")  page:Int,
         @Query("per_page")  perPage :Int,
     )  : List<CollectionsItemRp>

    companion object {
        operator fun invoke(retrofit: Retrofit): UnsplashApiService =
            retrofit.create(UnsplashApiService::class.java)
    }
}



//invoke(...) ở sâu bên trong companion object
//🧩 Nghĩa là:
//operator fun invoke(...) là toán tử đặc biệt trong Kotlin
//
//Cho phép bạn gọi UnsplashApiService(retrofit) như gọi hàm, dù UnsplashApiService là interface.
//
//🔍 Ví dụ:
//val api = UnsplashApiService(retrofit) , với invoke thì có thể gọi UnsplashApiService(retrofit) như 1 hàm/class
//
//👉 Thực ra là gọi:
//
//val api = UnsplashApiService.Companion.invoke(retrofit)
//🧠 Cách hiểu:
//invoke() là hàm đặc biệt, cho phép dùng tên class như hàm khởi tạo, nhưng tùy biến được.

//Câu hỏi bạn có thể có	Câu trả lời
//Tại sao dùng @Query?	Để thêm các tham số sau dấu ? trên URL
//Tại sao dùng suspend?	Vì Retrofit hỗ trợ coroutine → dễ viết bất đồng bộ
//Trả về kiểu gì?	List của model mà bạn định nghĩa tương ứng với JSON từ server
