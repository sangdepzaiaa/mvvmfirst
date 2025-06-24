package com.example.myapplication.demoviewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.math.log

class ShareViewModel(private var savedstateHandle: SavedStateHandle) :ViewModel() {
    private var counter = 0
    init {
        val savecounter = savedstateHandle.get<Int>("COUNTER")
        Log.d("savecounter","savecounter = $savecounter")
    }
    private var _mutableLivedata = SingleLiveEvent<Int>()
    var liveData: SingleLiveEvent<Int> = _mutableLivedata

    fun counterUp(){
        counter++
        _mutableLivedata.value = counter
        savecounterstate()
    }

    private fun savecounterstate(){
        savedstateHandle["COUNTER"] = counter
    }

}