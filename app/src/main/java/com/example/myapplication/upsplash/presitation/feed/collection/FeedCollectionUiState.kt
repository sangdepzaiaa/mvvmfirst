package com.example.myapplication.upsplash.presitation.feed.collection

import com.example.myapplication.upsplash.data.response.CollectionItemResponse
import com.squareup.moshi.Json

sealed interface FeedCollectionUiState{

    data object FirstPageLoading: FeedCollectionUiState
    data object FirstPageError: FeedCollectionUiState

    data class Content(
        val item: List<CollectionsItem>,
        val currentPage: Int,
        val nextPageState : NextPageState
    ): FeedCollectionUiState

    data class CollectionsItem(
        val id:String,
        val title:String,
        val description: String,
        val photocover: String
    ): FeedCollectionUiState

    enum class NextPageState {
       LOADING,
        NO_MORE_ITEMS,
        IDLE,
        ERROR
    }

}









//sealed interface FeedCollectionUiState{
//    data object FirstPageLoading: FeedCollectionUiState
//    data object FirstPageError: FeedCollectionUiState
//
//    data class Content(
//        val item: List<CollectionsItem>,
//        val currentPage:Int,
//        val nextPageState: NextPageState
//    ): FeedCollectionUiState
//
//
//    data class CollectionsItem (
//        val id: String,
//        val title :String,
//        val des: String ,
//        val photocover:String ,
//    )
//
//    enum class NextPageState {
//        LOADING,
//        IDLE,
//        NO_MORE_ITEMS,
//        ERROR
//
//    }
//
//}



////sealed interface FeedCollectionUiState
////Là kiểu cha tổng quát cho tất cả các trạng thái giao diện (UI state)
//// mà Fragment hoặc Activity cần biết để vẽ giao diện đúng.
//sealed interface FeedCollectionUiState {
//    //data object singeton object trạng thaí duy nhất
////    Đại diện cho trạng thái:
////    App đang tải dữ liệu trang đầu tiên (thường là lần đầu mở màn hình).
////    Dùng để hiển thị: loading spinner, progress bar...
//
//    //    không khai báo : FeedCollectionUiState, thì:
////    Nó không phải là một subtype của FeedCollectionUiState.
////    Do đó, khi bạn gõ FeedCollectionUiState. → nó sẽ không hiện ra SomethingElse.
////    Và bạn cũng không thể dùng nó trong when(uiState), vì không phải là một FeedCollectionUiState.
////Khi bạn gõ FeedCollectionUiState., IDE (Android Studio) sẽ hiển thị gợi ý
//// những đối tượng kế thừa nó trong cùng file.
//    data object FirstPageLoading : FeedCollectionUiState
//
//    //    Đại diện cho trạng thái:
////    Tải trang đầu tiên bị lỗi (mất mạng, lỗi server,...).
////    App nên hiển thị: thông báo lỗi, nút "Thử lại".
//    data object FirstPageError : FeedCollectionUiState
//
//    //    Đại diện cho trạng thái:
////    Đã tải thành công dữ liệu.
////    Chứa:
////    item: danh sách để hiển thị trong RecyclerView.
////    currentPage: đang ở trang nào.
////    nextPageState: đang tải thêm hay không, có lỗi không, còn dữ liệu không?
//    data class Content(
//        val item: List<CollectionItem>,
//        val currentPage: Int,
//        val nextPageState: NextPageState
//    ) : FeedCollectionUiState
//    //: FeedCollectionUiState dùng khi Khi trạng thái phức tạp, có thể mang dữ liệu riêng
//
//    // cấu trúc dữ liệu
//    data class CollectionItem(
//        // bỏ CollectionItem2
//        val id: String,
//        val title: String,
//        val des: String,
//        val coverPhoto: String,
//    )
//
//    //enum class trong Kotlin được dùng để khai báo một tập hợp hằng số định danh có ý nghĩa cụ thể.
//// Mỗi phần tử trong enum là một đối tượng duy nhất, dùng để biểu diễn trạng thái, lựa chọn, loại...
//    // enum dùng khi chỉ có danh sách giá trị đơn giản, cố định
//    enum class NextPageState {
//        LOADING,
//        ERROR,
//        IDLE,
//        NO_MORE_ITEMS
//    }
////    Điểm khác nhau	             enum class	                                  sealed interface / sealed class (ví dụ: FeedCollectionUiState)
////    ✅ Dùng khi nào?	Khi chỉ có danh sách giá trị đơn giản, cố định	       Khi trạng thái phức tạp, có thể mang dữ liệu riêng
////    🎯 Có mang dữ liệu riêng không?	Có thể, nhưng hạn chế	                     Rất linh hoạt: mỗi subclass có thể chứa dữ liệu riêng
////    🤖 Gợi ý IDE / when exhaustive	Có	                                             Có
////    🧠 Khả năng mở rộng	Khó mở rộng – phải sửa enum gốc	                           Dễ mở rộng – chỉ cần thêm subclass
////    📦 Tính kế thừa	Enum không kế thừa nhau	                                           Sealed interface cho phép đa kế thừa (interface)
////    📘 Ví dụ trong code bạn	NextPageState chỉ có LOADING, ERROR, IDLE, NO_MORE_ITEMS	FeedCollectionUiState có các trạng thái khác nhau và Content có thêm dữ liệu
//
////    | Ý muốn                                                     | Dùng gì                                |
////    | ---------------------------------------------------------- | -------------------------------------- |
////    | Biểu diễn **danh sách trạng thái đơn giản**                | `enum class`                           |
////    | Biểu diễn **trạng thái UI phức tạp, có dữ liệu khác nhau** | `sealed class` hoặc `sealed interface` |
////ví dụ
//    //→ Mỗi trạng thái là một loại dữ liệu riêng:
////    FirstPageLoading: không chứa gì
////
////    Content: chứa item, currentPage, nextPageState
////
////    ➡ Bạn không thể làm điều này bằng enum vì enum không cho từng phần tử có kiểu dữ liệu khác nhau.
//    //Là tập hợp trạng thái đơn giản, không kèm nhiều logic hay dữ liệu. Đủ dùng cho trạng thái phân trang.
//}

