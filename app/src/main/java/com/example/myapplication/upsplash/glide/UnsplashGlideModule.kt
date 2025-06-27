package com.example.myapplication.upsplash.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.myapplication.upsplash.UnsplashServiceLocator
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class UnsplashGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(
                UnsplashServiceLocator.okHttpClient
            )
        )
    }
}
//Unsplash API yêu cầu tất cả request (bao gồm cả ảnh) phải có header sau:
//Authorization: Client-ID YOUR_ACCESS_KEY
//Khi bạn dùng Glide để tải ảnh từ Unsplash như:
//Glide.with(context).load("https://api.unsplash.com/photo/...").into(imageView)
//Mà không gắn header Authorization, Unsplash sẽ trả về lỗi:
//401 Unauthorized
//Vì Glide mặc định dùng HttpURLConnection, không có chỗ để gắn interceptor hay header tùy chỉnh.
//Làm sao gắn header Authorization vào Glide?
//Bạn phải thay network stack của Glide bằng OkHttp — nơi bạn đã đặt sẵn interceptor gắn Authorization.
//
//Cách làm:
//Bạn có UnsplashServiceLocator.okHttpClient:
//val okHttpClient = OkHttpClient.Builder()
//    .addInterceptor { chain ->
//        val newRequest = chain.request().newBuilder()
//            .addHeader("Authorization", "Client-ID YOUR_ACCESS_KEY")
//            .build()
//        chain.proceed(newRequest)
//    }
//    .build()
//
//Trong UnsplashGlideModule, bạn ép Glide dùng client này:
//
//registry.replace(
//GlideUrl::class.java,
//InputStream::class.java,
//OkHttpUrlLoader.Factory(UnsplashServiceLocator.okHttpClient)
//)
//
//Vậy là từ giờ:
//Mỗi khi bạn gọi Glide.load(url) → Glide dùng OkHttp
//Mỗi request ảnh gửi lên Unsplash đều có header Authorization: Client-ID ...
//Kết quả: server trả ảnh hợp lệ, không bị 401

//Tóm tắt ngắn gọn
//Mục tiêu	                                 Giải pháp
//Unsplash yêu cầu Authorization	        Phải gắn header Client-ID vào tất cả request
//Glide không gắn header được	             Phải thay loader Glide dùng OkHttp
//Làm sao thay loader	                     Dùng @GlideModule + OkHttpUrlLoader.Factory

//Ví dụ ảnh bị lỗi (nếu không gắn header):
//
//W/Glide: Load failed for https://api.unsplash.com/photos/... with size [500x500]
//HTTP 401 Unauthorized
//Biểu hiện: Ảnh trắng / không hiển thị.