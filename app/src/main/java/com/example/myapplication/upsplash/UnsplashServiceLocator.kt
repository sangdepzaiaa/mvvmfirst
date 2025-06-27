package com.example.myapplication.upsplash

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle

import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.BuildConfig
import com.example.myapplication.upsplash.data.UnsplashApiService
import com.example.myapplication.upsplash.data.intercepter.AuthorizationInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Array.get

object UnsplashServiceLocator  {
   const val BASE_URL = "https://api.unsplash.com/"

   var unsplash : UnsplashApplication?=null

   @MainThread
   fun initwith(app : UnsplashApplication){
       unsplash = app
   }

    @get:MainThread
    val appli: UnsplashApplication
        get() = checkNotNull(unsplash){
            "OK"
        }

    val moshi:Moshi by lazy {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    val httpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG){
                HttpLoggingInterceptor.Level.BODY
            }else{
                HttpLoggingInterceptor.Level.NONE
            }
        }

    val authorizationInterceptor:AuthorizationInterceptor
        get() = AuthorizationInterceptor(clientId = BuildConfig.UNSPLASH_CLIENT_ID)

    val okHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    }

    val unsplashApiService:UnsplashApiService by lazy { UnsplashApiService.invoke(retrofit) }
}