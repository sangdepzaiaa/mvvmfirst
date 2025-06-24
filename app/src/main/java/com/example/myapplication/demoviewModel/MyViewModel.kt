package com.example.myapplication.demoviewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyRepository{
    suspend fun fetData(): String{
        delay(2000)
        return  "ewrewr : ${System.currentTimeMillis()}"
    }
}
class MyViewModel(private val myRepository: MyRepository) : ViewModel() {
    private val mutableLiveData = MutableLiveData<String>()
     val liveData : LiveData<String> = mutableLiveData
    var counter: Int = 0

    fun getDataFactory(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                myRepository.fetData()
            }
            mutableLiveData.value = data

        }
    }
}