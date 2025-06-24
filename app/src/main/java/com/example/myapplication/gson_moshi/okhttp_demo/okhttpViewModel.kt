package com.example.myapplication.gson_moshi.okhttp_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface DemookhttpUistate{
    data object idle: DemookhttpUistate
    data object Loading: DemookhttpUistate
    data class Content(val todos:List<todoitemrespince>):DemookhttpUistate
    data class Error(val throwable: Throwable) : DemookhttpUistate
}
class okhttpViewModel : ViewModel(){
    private val todoApiService by lazy {
        provideTodoApiService()
    }
    private val _uistate = MutableLiveData<DemookhttpUistate>(DemookhttpUistate.idle)
    val uistate:LiveData<DemookhttpUistate> = _uistate

    fun getTodos2(){
        viewModelScope.launch {
            _uistate.value = DemookhttpUistate.Loading
            try {
                val todos = todoApiService.getTodos()
                _uistate.value = DemookhttpUistate.Content(todos)
            }catch (cancel : CancellationException){
                throw cancel
            }catch (e :Exception){
                _uistate.value= DemookhttpUistate.Error(e)
            }
        }
    }
}