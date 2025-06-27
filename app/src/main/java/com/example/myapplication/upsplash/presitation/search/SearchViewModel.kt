package com.example.myapplication.upsplash.presitation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.myapplication.upsplash.data.UnsplashApiService
import com.example.myapplication.upsplash.presitation.debounce
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionUiState
import kotlinx.coroutines.Dispatchers

class SearchViewModel(val unsplashApiService: UnsplashApiService): ViewModel() {

    private val searchMutable = MutableLiveData<String>()
    val searchLiveData : LiveData<String> get() = searchMutable

//    init {
//        val data:LiveData<Int> = liveData{
//            delay(3000)
//            emit(3)
//        }
//    }

    val searchResultLivedata:LiveData<List<FeedCollectionUiState.CollectionsItem>> = searchLiveData
        .debounce(duration = 650L, coroutineScope = viewModelScope)
        .distinctUntilChanged()
        .switchMap { query:String ->
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                try {
                    val result:List<FeedCollectionUiState.CollectionsItem> =
                        unsplashApiService.getSearch(query,1,10)
                        .results
                        .map { coverPhoto ->
                            FeedCollectionUiState.CollectionsItem(
                                id = coverPhoto.id,
                                title = coverPhoto.description.orEmpty(),
                                description = coverPhoto.user.name,
                                photocover = coverPhoto.urls.regular,
                            )
                        }
                    emit(result)
                }catch (e: Exception){
                        emit(emptyList())
                }
            }
        }
    fun setSearchQuery(query :String){
        searchMutable.value = query
    }
}