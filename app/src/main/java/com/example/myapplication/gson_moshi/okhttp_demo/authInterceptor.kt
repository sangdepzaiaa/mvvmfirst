package com.example.myapplication.gson_moshi.okhttp_demo

import android.webkit.HttpAuthHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class JwtManager{
    suspend fun getJwtfromlocal(): String?{
        println(" >>> getJwtfromlocal")
        delay(200)
        return "OK"
    }
    suspend fun cleargetJwtfromlocal(){
        println(" >>> cleargetJwtfromlocal")
        delay(200)
    }
}
class authInterceptor(private val  jwtTokenManager: JwtManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        println(">>> AuthInterceptor START thread=${Thread.currentThread().name}")

        val request = chain.request()

        val token = runBlocking {
            jwtTokenManager.getJwtfromlocal()
        }
        val newRequest = if(token == null){
             request
        }else{
            request.newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "Baron ${token}"
                )
                .build()
        }
        val response = chain.proceed(newRequest)

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED){
            runBlocking { jwtTokenManager.cleargetJwtfromlocal() }
        }

        println("<<< AuthInterceptor END thread=${Thread.currentThread().name}")
        return response
    }
}