package com.example.myapplication.upsplash.presitation.feed.collection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.upsplash.data.UnsplashApiService
import com.example.myapplication.upsplash.data.response.CollectionsItemRp
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class feedcollectionViewModel(val apiService: UnsplashApiService) : ViewModel() {


    companion object {
        val LOG_TAG = feedcollectionViewModel::class.java.simpleName

        const val PER_PAGE = 30

        fun getNextPagestate(pageSize: Int): FeedCollectionUiState.NextPageState {
            return if (pageSize < PER_PAGE) {
                FeedCollectionUiState.NextPageState.NO_MORE_ITEMS
            } else {
                FeedCollectionUiState.NextPageState.IDLE
            }
        }
    }

    val mutable = MutableLiveData<FeedCollectionUiState>(FeedCollectionUiState.FirstPageLoading)
    val liveData: LiveData<FeedCollectionUiState> get() = mutable

    init {
        loadingfirstpage()
    }

    private fun loadingfirstpage() {
        viewModelScope.launch {
            mutable.value = FeedCollectionUiState.FirstPageLoading
            try {
                Log.d(LOG_TAG, "loadingfirstpage start")
                val responseItem = apiService.getCollections(page = 1, perPage = PER_PAGE)
                val collectionItem = responseItem.map { it.toUIitem() }

                mutable.value = FeedCollectionUiState.Content(
                    item = collectionItem,
                    currentPage = 1,
                    nextPageState = getNextPagestate(pageSize = collectionItem.size),
                )

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(LOG_TAG, "error", e)
                mutable.value = FeedCollectionUiState.FirstPageError
            }

        }
    }

    fun loadnextpage() {

        when (val currentState = mutable.value!!) {
            FeedCollectionUiState.FirstPageLoading,
            FeedCollectionUiState.FirstPageError,
            -> return

            is FeedCollectionUiState.Content -> {
                when (currentState.nextPageState) {
                    FeedCollectionUiState.NextPageState.NO_MORE_ITEMS -> return
                    FeedCollectionUiState.NextPageState.LOADING -> return
                    FeedCollectionUiState.NextPageState.ERROR -> return
                    FeedCollectionUiState.NextPageState.IDLE ->
                        loadNextpageInternal(currentState)

                }
            }

            else -> error("error")
        }
    }


    private fun loadNextpageInternal(currentState: FeedCollectionUiState.Content) {
        viewModelScope.launch {
            mutable.value = currentState.copy(
                nextPageState = FeedCollectionUiState.NextPageState.LOADING,
            )
            val nextpage = currentState.currentPage + 1
            try {
                Log.d(LOG_TAG, "loadNextpage: start with page $nextpage")
                val responseItem = apiService.getCollections(page = nextpage, perPage = PER_PAGE)
                val newpageUIitem = responseItem.map { it.toUIitem() }

                mutable.value = currentState.copy(
                    item = (currentState.item + newpageUIitem).distinctBy { it.id },
                    currentPage = nextpage,
                    nextPageState = getNextPagestate(pageSize = newpageUIitem.size),
                )

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(LOG_TAG, "error", e)
                mutable.value = currentState.copy(
                    nextPageState = FeedCollectionUiState.NextPageState.ERROR,
                )
            }

        }
    }

    fun CollectionsItemRp.toUIitem(): FeedCollectionUiState.CollectionsItem {
        return FeedCollectionUiState.CollectionsItem(
            id = id,
            title = title,
            description = description ?: "no des",
            coverPhotoUrl = coverPhoto.urls.regular,
        )
    }
}
