package com.example.myapplication.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// ui/viewmodel/CollectionViewModel.kt
class CollectionViewModel(private val repository: CollectionRepository) : ViewModel() {

    private val _collections = MutableLiveData<List<CollectionItemResponse>>()
    val collections: LiveData<List<CollectionItemResponse>> get() = _collections

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadCollections(page: Int = 1, perPage: Int = 10) {
        viewModelScope.launch {
            try {
                val result = repository.fetchCollections(page, perPage)
                _collections.value = result
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
