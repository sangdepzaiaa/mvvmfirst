package com.example.myapplication.upsplash.presitation.search.photo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.databinding.FragmentSearchPhotoBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionItemAdapter
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

    private val feedCollectionItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
        FeedCollectionItemAdapter(
            onItemClick = { },
            requestManager = Glide.with(this@SearchPhotoFragment)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        binVM()
    }

    private fun binVM() {
        vm.searchResultLivedata.observe(
            viewLifecycleOwner,
            feedCollectionItemAdapter::submitList
        )
    }

    private fun setupViews() {
        binding.recyclerview.run {
            setHasFixedSize(true)
            setItemViewCacheSize(50)
            layoutManager = LinearLayoutManager(context)
            adapter = feedCollectionItemAdapter
        }
    }


    companion object{
        fun inStance() = SearchPhotoFragment()
    }
}