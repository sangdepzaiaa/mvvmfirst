package com.example.myapplication

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


suspend fun fun1() : Int{
    delay(3000L)
    return 32
}
fun main() = runBlocking {
    val def:Deferred<Int> = async {
   return@async 40
    }
    val result : Int = def.await()
    println(result)
}