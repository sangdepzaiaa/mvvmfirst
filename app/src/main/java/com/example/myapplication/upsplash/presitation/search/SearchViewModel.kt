package com.example.myapplication.upsplash.presitation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.myapplication.upsplash.data.UnsplashApiService
import com.example.myapplication.upsplash.data.response.CollectionsItemsRp
import com.example.myapplication.upsplash.presitation.debounce
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import okhttp3.Dispatcher
import retrofit2.http.Query

class SearchViewModel(var unsplashApiService: UnsplashApiService):ViewModel(){

    val mutableLiveData = MutableLiveData<String>()
    val liveData:LiveData<String> get() = mutableLiveData

    val searchResult: LiveData<List<FeedCollectionUiState.CollectionsItem>>  = liveData
        .debounce(duration = 650L, coroutineScope = viewModelScope)
        .distinctUntilChanged()
        .switchMap {query ->
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO ) {
                try {
                    val result: List<FeedCollectionUiState.CollectionsItem> =
                        unsplashApiService.getSearchs(query,1,10)
                            .result
                            .map { cover ->
                                FeedCollectionUiState.CollectionsItem(
                                    id = cover.id,
                                    title = cover.description.orEmpty(),
                                    description = cover.user.name,
                                    photocover = cover.urls.regular
                                )
                            }
                    emit(result)
                }catch (e: Exception){
                    emit(emptyList())
                }
            }
        }

    fun setQuery(query: String){
        mutableLiveData.value = query
    }
}





