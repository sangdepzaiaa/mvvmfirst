package com.example.myapplication.upsplash.presitation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.databinding.FragmentSearchhBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.presitation.search.photo.SearchPhotoFragment
import com.example.myapplication.upsplash.presitation.search.user.SearchUserFragment
import com.google.android.material.tabs.TabLayoutMediator


class SearchhFragment:BaseFragment1WithViewBinding<FragmentSearchhBinding>(
    inflateViewBinding = FragmentSearchhBinding::inflate,
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnview()
        setupViewpager()
    }

    private fun setupViewpager() {
        binding.run {
            toolbar.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            searchEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    vm.setQueryLivedata(p0.toString())
                }

            })
        }
    }

    private fun setupOnview() {
        binding.viewpager.run {
            adapter = viewpagerAdapter(this@SearchhFragment)

            TabLayoutMediator(
                binding.tablayout,
                this
            ){tab, position ->
                tab.text = when(position){
                    0 -> "photo"
                    1 -> "user"
                    else -> error("error $position" )
                }
            }.attach()
        }
    }
}

class viewpagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return  when(position){
            0 -> SearchPhotoFragment.newInstance()
            1 -> SearchUserFragment.instance()
            else -> error("error $position")
        }
    }
}

//activityViewModels :Nó giúp bạn dùng chung ViewModel giữa nhiều Fragment trong cùng 1 Activity.
//ViewModel lúc này không thuộc Fragment, mà thuộc Activity → mọi Fragment trong Activity đó dùng
//chung cùng một thể hiện (instance).
//
//So sánh viewModels vs activityViewModels
//viewModels()	                           activityViewModels()
//ViewModel chỉ sống trong Fragment	        ViewModel thuộc Activity
//Chỉ dùng được trong chính Fragment đó	   Các Fragment trong cùng Activity đều dùng được
//Mỗi Fragment có instance riêng	           Dùng chung ViewModel giữa các Fragment
//
//Tình huống thực tế: Vì sao SearchFragment dùng activityViewModels
//🔸 Mục đích: chia sẻ dữ liệu tìm kiếm (query, kết quả) giữa nhiều Fragment.
//🔸 Ví dụ UI:
//MainActivity
//└── FeedFragment     ← chỉ hiển thị ảnh bình thường
//└── SearchFragment   ← nhập query tìm kiếm
//└── ResultFragment   ← hiển thị kết quả từ SearchViewModel
//
//Khi SearchFragment cập nhật query (searchLiveData.value = "cat"), thì ResultFragment tự động
//nhận được kết quả từ SearchViewModel, vì cả 2 Fragment đang dùng chung ViewModel đó từ activityViewModels.
//
//Vì sao FeedFragment không cần activityViewModels
//Vì FeedFragment không cần chia sẻ dữ liệu với Fragment nào khác → nó tự khai báo ViewModel của riêng nó
//(viewModels() hoặc không dùng gì nếu không cần).
//
//Khi nào dùng activityViewModels()?
//Tình huống	                                                             Có dùng activityViewModels?
//Chia sẻ kết quả tìm kiếm giữa nhiều Fragment	                               ✅
//Fragment cần cập nhật UI dựa trên dữ liệu chung (giỏ hàng, người dùng…)  	✅
//Fragment độc lập, dữ liệu riêng	                                            ❌ dùng viewModels() là đủ
//
//Kết luận
//activityViewModels dùng khi bạn muốn chia sẻ ViewModel giữa các Fragment trong cùng Activity.
//
//viewModels dùng khi mỗi Fragment tự quản lý ViewModel riêng của nó.
//
//Trong app thực tế, tìm kiếm, giỏ hàng, trạng thái người dùng đăng nhập thường dùng activityViewModels.