package com.example.myapplication.demo_API

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class demoCallapiViewModel(private val todoService: todoService): ViewModel() {
      private val _mutableLiveData = MutableLiveData<todoUIstate>()
    val liveData : LiveData<todoUIstate> = _mutableLiveData

    fun todogetSync(){ //làm block main thread
        val result: Response<TodoResponse> = todoService.getTodo().execute()

        if (result.isSuccessful){
            _mutableLiveData.value = todoUIstate.susecc(result.body()!!)

        }else{
            _mutableLiveData.value = todoUIstate.error(Throwable(result.message()))
        }
    }

    fun todogetAsync(){//không làm block main thread
        _mutableLiveData.value = todoUIstate.loading

        todoService.getTodo().enqueue(object : Callback<TodoResponse>{
            override fun onResponse(p0: Call<TodoResponse>, p1: Response<TodoResponse>) {
                if (p1.isSuccessful){
                    _mutableLiveData.value = todoUIstate.susecc(p1.body()!!)
                }
            }

            override fun onFailure(p0: Call<TodoResponse>, p1: Throwable) {
                _mutableLiveData.value = todoUIstate.error(p1)
            }

        })
    }

    fun gettodosusoend(){  // gioong c2 nhưng là suspend fun
        _mutableLiveData.value = todoUIstate.loading

        viewModelScope.launch {
            try{
                val result: TodoResponse = withContext(Dispatchers.IO){
                    todoService.getTodoSuspen()
                }
                _mutableLiveData.value = todoUIstate.susecc(result)
            }catch (cancel : CancellationException){
                throw cancel
            }catch (throwable : Throwable){
                _mutableLiveData.value = todoUIstate.error(throwable)
            }
        }
    }

    fun getListUser(){
        _mutableLiveData.value = todoUIstate.loading

        viewModelScope.launch {
            try{
                val result:List<User> = withContext(Dispatchers.IO){
                    todoService.getList()
                }
                _mutableLiveData.value = todoUIstate.suseccList(result)
            }catch (cancel: CancellationException){
                throw  cancel
            }catch (throwable: Throwable){
                _mutableLiveData.value = todoUIstate.error(throwable)
            }
        }
    }

    fun posUserSuspend(){
        _mutableLiveData.value = todoUIstate.loading

        var userRequest = PostRequest(id = 88, lang = "ss", name = "sss")
        viewModelScope.launch {
            try{
                val result: PostResponce = withContext(Dispatchers.IO){
                    todoService.postusername(userRequest)
                }
                _mutableLiveData.value = todoUIstate.suseccListResponce(result)
            }catch (cancel : CancellationException){
                throw  cancel
            }catch (throwable: Throwable){
                _mutableLiveData.value = todoUIstate.error(throwable)
            }
        }
    }
}