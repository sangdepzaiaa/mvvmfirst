package com.example.myapplication.upsplash.presitation.search.photo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.Fragments.DemoFragment
import com.example.myapplication.databinding.FragmentSearchPhotoBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionItemAdapter
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionUiState
import com.example.myapplication.upsplash.presitation.search.SearchViewModel


class SearchPhotoFragment : BaseFragment1WithViewBinding<FragmentSearchPhotoBinding>(
    inflateViewBinding = FragmentSearchPhotoBinding::inflate,
){
    private val vm by activityViewModels<SearchViewModel>(
        factoryProducer = {
            viewModelFactory {
                addInitializer(SearchViewModel::class){
                    SearchViewModel(unsplashApiService = UnsplashServiceLocator.unsplashApiService)
                }
            }
        }
    )

    private val feedCollectionItemAdapter by lazy {
        FeedCollectionItemAdapter(
            onClick = ::onClick,
            requestManager = Glide.with(this@SearchPhotoFragment)
        )
    }

    private fun onClick(collectionsItem: FeedCollectionUiState.CollectionsItem) {
      startActivity(Intent(requireContext(),DemoFragment::class.java))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindViewdel()
    }

    private fun bindViewdel() {
        vm.searchResultLivadata.observe(
            viewLifecycleOwner,
            feedCollectionItemAdapter::submitList)
    }

    private fun setupView() {
        binding.recyclerview.run {
            setHasFixedSize(true)
            setItemViewCacheSize(40)
            layoutManager = LinearLayoutManager(context)
            adapter = feedCollectionItemAdapter
        }
    }

    companion object{
        fun newInstance() = SearchPhotoFragment()
    }
}
//Thành phần                   	Ý nghĩa	                                              Điều xảy ra khi chạy
//vm.searchResultLivedata  	LiveData<List<FeedCollectionUiState.CollectionsItem>>     Mỗi khi ViewModel emit
//                            – danh sách kết quả tìm kiếm do SearchViewModel phát.     list mới (sau khi người dùng tìm),
//                                                                                       LiveData sẽ thông báo Observer.
//
//viewLifecycleOwner	        Lifecycle của Fragment.	                                   Bảo đảm chỉ quan sát khi View còn sống;
//                                                                                     khi onDestroyView() →
//                                                                                      tự huỷ Observer, tránh leak.
//feedCollection
//ItemAdapter::submitList	    Method reference tới hàm submitList() của ListAdapter.	  LiveData gửi list mới →
//                                                                                      submitList(newList) →DiffUtil tính chênh lệch
//                                                                                      → RecyclerView cập nhật UI
//
//Vì sao dùng ::submitList?
//Ngắn gọn hơn lambda:
//{ list -> feedCollectionItemAdapter.submitList(list) }
//Trực tiếp “nối” dòng dữ liệu LiveData vào adapter.
//
//Luồng hoạt động:
//Người dùng gõ cat ✏️ → SearchViewModel gọi API → emit List<CollectionsItem> qua searchResultLivedata.
//
//observe() nhận thông báo → gọi ngay submitList(newList).
//
//ListAdapter với DiffUtil:
//
//So sánh list cũ–mới.
//
//Chỉ chèn / xoá / cập nhật item thay đổi → UI mượt, không nháy.
//
//RecyclerView hiển thị kết quả tìm kiếm mới nhất.
//
//Lưu ý:
//searchResultLivedata nên được tạo bằng switchMap + debounce (như bạn đã làm) để tránh gọi API liên tục.
//
//Đặt tên hàm bindViewModel() hoặc observeViewModel() cho rõ vai trò.
//
//Thành phần	Vai trò
//mutable.value = ...	Cập nhật dữ liệu mới trong ViewModel
//LiveData	Phát hiện thay đổi và gọi observer
//submitList(...)	Nhận danh sách mới → cập nhật RecyclerView UI

