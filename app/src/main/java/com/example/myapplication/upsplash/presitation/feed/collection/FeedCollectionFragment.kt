package com.example.myapplication.upsplash.presitation.feed.collection

import android.content.Intent
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
import com.example.myapplication.Fragments.DemoFragment
import com.example.myapplication.databinding.FragmentFeedCollectionBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.domain.model.CollectionItem

class FeedCollectionFragment: BaseFragment1WithViewBinding<FragmentFeedCollectionBinding>(
    inflateViewBinding = FragmentFeedCollectionBinding::inflate,
){
    private val vm by viewModels<feedcollectionViewModel>(
        factoryProducer = {
            viewModelFactory {
                addInitializer(feedcollectionViewModel::class){
                    feedcollectionViewModel(apiService = UnsplashServiceLocator.unsplashApiService)
                }
            }
        }
    )

    private val feedCollectionItemAdapter by lazy(LazyThreadSafetyMode.NONE)
    { FeedCollectionItemAdapter(
        onClick = ::onClick,
        requestManager = Glide.with(requireContext())
    ) }

    private fun onClick(collectionsItem: FeedCollectionUiState.CollectionsItem) {
       Toast.makeText(context,"${collectionsItem.title}" ,Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindViewModel()
    }



    private fun setupView() {
        binding.recyclerView.run {
            setHasFixedSize(true)
            adapter = feedCollectionItemAdapter
            layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun bindViewModel() {
        vm.liveData.observe(viewLifecycleOwner,::render)

        val linearLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager

        binding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                println(">>> onScrolled: $dy")
                if ( dy > 0 &&linearLayoutManager.findLastVisibleItemPosition() + VISIBLE_THRESHOLD
                     >= linearLayoutManager.itemCount)
                 {
                    println(">>> onScrolled[*]")
                     vm.loadnextpage()
                }
            }
        })
    }

    fun render(feedCollectionUiState: FeedCollectionUiState?) {
        when(feedCollectionUiState){
                FeedCollectionUiState.FirstPageLoading -> {
                    binding.progressBar.isVisible = true
                    binding.textError.isGone= true
                    feedCollectionItemAdapter.submitList(emptyList())
                }
                is FeedCollectionUiState.Content -> {
                    binding.progressBar.isGone = true
                    binding.textError.isGone = true
                    feedCollectionItemAdapter.submitList(feedCollectionUiState.item)
                }
                FeedCollectionUiState.FirstPageError -> {
                    binding.progressBar.isGone = true
                    binding.textError.isVisible = true
                    binding.textError.text = "Đã có lỗi xảy ra,xin thử lại"
                    feedCollectionItemAdapter.submitList(emptyList())
                }

             else -> error("error")

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.recyclerView.adapter = null
    }

    companion object{
        fun newInstance() = FeedCollectionFragment()
        private const val VISIBLE_THRESHOLD = 3
    }

}

