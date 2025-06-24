package com.example.myapplication.upsplash.presitation.feed.collection

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.databinding.FragmentFeedCollectionBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.domain.model.CollectionItem


class FeedCollectionFragment : BaseFragment1WithViewBinding<FragmentFeedCollectionBinding>(
    inflateViewBinding = FragmentFeedCollectionBinding::inflate
) {
    private val vm by viewModels<feedcollectionViewModel>(
          factoryProducer = {
              viewModelFactory {
                  addInitializer(feedcollectionViewModel::class){
                      feedcollectionViewModel(apiService = UnsplashServiceLocator.unsplashApiService)
                  }
              }
          }
    )
   private val feedCollectionItemAdapter by lazy(LazyThreadSafetyMode.NONE) {
       FeedCollectionItemAdapter(
           onItemClick = ::onItemClick,
           requestManager = Glide.with(this@FeedCollectionFragment)
       )
   }

    private fun onItemClick(collectionItem: FeedCollectionUiState.CollectionsItem) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindViewModel()
    }

    override fun onDestroy() {
        binding.recyclerView.adapter = null
        super.onDestroy()

    }

    fun setupView(){
        binding.recyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = feedCollectionItemAdapter
        }
    }

    fun bindViewModel(){
        vm.liveData.observe(viewLifecycleOwner,::render)

        val linearLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("onScrolled $dy")
                if(dy > 0){
                    if (linearLayoutManager.findLastVisibleItemPosition() + VISIBLE >= linearLayoutManager.itemCount ){
                        println("onScrolled *")
                        vm.loadnextpage()
                    }
                }
            }
        })

    }
    private fun render(uiState: FeedCollectionUiState) {
        when (uiState) {
            is FeedCollectionUiState.FirstPageLoading -> {
                binding.run {
                    progressBar.isVisible = true
                    textError.isGone = true
                }
                feedCollectionItemAdapter.submitList(emptyList())
            }

            is FeedCollectionUiState.FirstPageError -> {
                binding.run {
                    progressBar.isGone = true
                    textError.isVisible = true
                    textError.text = "Đã xảy ra lỗi. Vui lòng thử lại."
                }
                feedCollectionItemAdapter.submitList(emptyList())
            }

            is FeedCollectionUiState.Content -> {
                binding.run {
                    progressBar.isGone = true
                    textError.isGone = true
                }

                feedCollectionItemAdapter.submitList(uiState.item)
            }
            else -> error("error")
        }
    }


    companion object{
        fun newInstance() = FeedCollectionFragment()
        const val VISIBLE = 3
    }

}