package com.example.myapplication.upsplash.presitation.feed.collection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.upsplash.data.UnsplashApiService
import com.example.myapplication.upsplash.data.response.CollectionsItemRp
import com.example.myapplication.upsplash.data.response.CollectionsItemsRp
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

    fun CollectionsItemsRp.toUIitem(): FeedCollectionUiState.CollectionsItem {
        return FeedCollectionUiState.CollectionsItem(
            id = id,
            title = title,
            description = description ?: "no des",
            photocover = coverPhoto.urls.regular,
        )
    }
}

//class feedcollectionViewModel(val apiService: UnsplashApiService) : ViewModel() {
//
//    companion object {
//        val LOG_TAG = feedcollectionViewModel::class.java.simpleName
//
//        const val PER_PAGE = 30
//
//        fun getNextPagestate(pageSize: Int): FeedCollectionUiState.NextPageState {
//            return if (pageSize < PER_PAGE) {
//                FeedCollectionUiState.NextPageState.NO_MORE_ITEMS
//            } else {
//                FeedCollectionUiState.NextPageState.IDLE
//            }
//        }
//    }
//
//    val mutable = MutableLiveData<FeedCollectionUiState>(FeedCollectionUiState.FirstPageLoading)
//    val liveData: LiveData<FeedCollectionUiState> get() = mutable
//
//    init {
//        loadingfirstpage()
//    }
//
//    private fun loadingfirstpage() {
//        viewModelScope.launch {
//            mutable.value = FeedCollectionUiState.FirstPageLoading
//            try {
//                Log.d(LOG_TAG, "loadingfirstpage start")
//                val responseItem = apiService.getCollections(page = 1, perPage = PER_PAGE)
////                Phần .map { ... } là một hàm ánh xạ (mapping function) trong Kotlin
////                dùng để chuyển đổi từng phần tử trong danh sách sang dạng mới.
////                it là từng phần tử trong responseItem, tức là từng CollectionsItemRp
////                toUIitem() là hàm mở rộng hoặc function thường bạn đã viết như sau
////fun CollectionsItemRp.toUIitem(): FeedCollectionUiState.CollectionsItem
////                Mục tiêu là chuyển đổi dữ liệu gốc từ API → sang dữ liệu UI để RecyclerView hiển thị được.
////                từ object -> String
//                val collectionItem = responseItem.map { it.toUIitem() }
//
//                mutable.value = FeedCollectionUiState.Content(
//                    item = collectionItem,
//                    currentPage = 1,
//                    nextPageState = getNextPagestate(pageSize = collectionItem.size),
//                )
//
//            } catch (e: CancellationException) {
//                throw e
//            } catch (e: Exception) {
//                Log.e(LOG_TAG, "error", e)
//                mutable.value = FeedCollectionUiState.FirstPageError
//            }
//
//        }
//    }
//
//    fun loadnextpage() {
//
//        when (val currentState = mutable.value!!) {
//            FeedCollectionUiState.FirstPageLoading,
//            FeedCollectionUiState.FirstPageError,
//            -> return
//
//            is FeedCollectionUiState.Content -> {
//                when (currentState.nextPageState) {
//                    FeedCollectionUiState.NextPageState.NO_MORE_ITEMS -> return
//                    FeedCollectionUiState.NextPageState.LOADING -> return
//                    FeedCollectionUiState.NextPageState.ERROR -> return
//                    FeedCollectionUiState.NextPageState.IDLE ->
//                        loadNextpageInternal(currentState)
//
//                }
//Kotlin tự động chuyển (cast) kiểu của biến sang class con tương ứng khi nó xác định rõ ràng bằng điều kiện như is.
//val x: Any = "Hello"
//
//if (x is String) {
//    println(x.length) // Kotlin hiểu x là String, không cần ép kiểu
//}
//
//||
//when (val currentState = mutable.value!!) {
//    is FeedCollectionUiState.Content -> {
//        // Lúc này currentState chính là FeedCollectionUiState.Content
//        // nên dùng được currentState.nextPageState
//    }
//            }
//
//            else -> error("error")
//        }
//    }
//
//
//    private fun loadNextpageInternal(currentState: FeedCollectionUiState.Content) {
//        viewModelScope.launch {
//Gọi copy() để tạo bản sao mới từ data class Content, với item và currentPage giữ nguyên, chỉ đổi nextPageState
// cái gì trong () của copy là cái đócjc đổi mới, mấy cái còn lại được copy
//Ví dụ cụ thể:
//Giả sử currentState đang chứa:
//Content(
//item = listOf(30 item),
//currentPage = 1,
//nextPageState = IDLE
//)
//
//Khi gọi:
//mutable.value = currentState.copy(
//nextPageState = FeedCollectionUiState.NextPageState.LOADING
//)
//
//Thì LiveData cập nhật thành:
//Content(
//item = listOf(30 item),
//currentPage = 1,
//nextPageState = LOADING
//)
//UI biết đang load trang 2, có thể hiện progress indicator ở cuối danh sách.
//
//Vì sao cần copy()?
//FeedCollectionUiState.Content là data class, nên nó có sẵn hàm copy() để tạo bản sao mới với một vài giá trị thay đổi.
//
//Không cần tạo lại toàn bộ object thủ công.
//
//Giúp bất biến hóa (immutable) dữ liệu → an toàn và dễ debug hơn.
//


//            mutable.value = currentState.copy(
//                nextPageState = FeedCollectionUiState.NextPageState.LOADING,
//            )
//            val nextpage = currentState.currentPage + 1
//            try {
//                Log.d(LOG_TAG, "loadNextpage: start with page $nextpage")
//                val responseItem = apiService.getCollections(page = nextpage, perPage = PER_PAGE)
//                val newpageUIitem = responseItem.map { it.toUIitem() }
//
//                mutable.value = currentState.copy(
//                    item = (currentState.item + newpageUIitem).distinctBy { it.id },
//                    currentPage = nextpage,
//                    nextPageState = getNextPagestate(pageSize = newpageUIitem.size),
//                )
//
//            } catch (e: CancellationException) {
//                throw e
//            } catch (e: Exception) {
//                Log.e(LOG_TAG, "error", e)
//                mutable.value = currentState.copy(
//                    nextPageState = FeedCollectionUiState.NextPageState.ERROR,
//                )
//            }
//
//        }
//    }
//
//    fun CollectionsItemRp.toUIitem(): FeedCollectionUiState.CollectionsItem {
//        return FeedCollectionUiState.CollectionsItem(
//            id = id,
//            title = title,
//            description = description ?: "no des",
//            coverPhotoUrl = coverPhoto.urls.regular,
//        )
//    }
//}
//Hàm mở rộng (Extension Function) là gì?
//Là cách thêm hàm mới vào một kiểu dữ liệu có sẵn (mà không cần sửa lớp đó).
//Cú pháp:  fun TênLớp.tênHàmMởRộng(...) { ... }
//fun String.sayHello() {
//    println("Hello, $this")
//}
//
//val name = "Sang"
//name.sayHello() // → In ra: Hello, Sang

//Biểu hiện nhận biết
//Nếu bạn gõ someObject. rồi thấy các hàm như toUIitem() hiện ra → đó có thể là extension function.
//
//Các IDE như Android Studio thường hiện thêm dấu Extension hoặc hiển thị tên file định nghĩa hàm đó.

