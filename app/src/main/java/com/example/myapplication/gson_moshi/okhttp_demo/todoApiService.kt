package com.example.myapplication.gson_moshi.okhttp_demo

import com.example.myapplication.BuildConfig
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface todoApiService {
    @GET("todos")
    suspend fun getTodos(): List<todoitemrespince>
}

fun provideTodoApiService(): todoApiService =
    provideRetrofit(
        okHttpClient = provideOkhttpClient(),
        moshi = provideMoshi()
    ).create()

private fun provideMoshi(): Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
        .build()

private fun provideOkhttpClient() : OkHttpClient{
    return OkHttpClient.Builder()
        .connectTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30,java.util.concurrent.TimeUnit.SECONDS)
        .addInterceptor(customHeaderInterceptor())
        .addInterceptor(authInterceptor(JwtManager()))
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor.Level.BODY
                }else{
                    HttpLoggingInterceptor.Level.NONE
                }
            }
        )
        .build()
}

private fun provideRetrofit( okHttpClient: OkHttpClient,moshi: Moshi): Retrofit{
  return Retrofit.Builder()
      .baseUrl("https://jsonplaceholder.typicode.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
}