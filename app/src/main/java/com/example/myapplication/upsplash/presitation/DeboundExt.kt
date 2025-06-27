package com.example.myapplication.upsplash.presitation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> LiveData<T>.debounce(duration: Long, coroutineScope: CoroutineScope): LiveData<T> {
    val mdl = MediatorLiveData<T>()

    var job: Job? = null

    mdl.addSource(this) { value ->
        job?.cancel()
        job = coroutineScope.launch {
            delay(timeMillis = duration)
            mdl.value = value
        }
    }

    return mdl
}