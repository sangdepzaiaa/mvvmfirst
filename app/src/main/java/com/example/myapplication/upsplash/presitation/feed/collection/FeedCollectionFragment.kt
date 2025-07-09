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
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.bumptech.glide.Glide
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.Fragments.DemoFragment
import com.example.myapplication.databinding.FragmentFeedCollectionBinding
import com.example.myapplication.upsplash.UnsplashServiceLocator
import com.example.myapplication.upsplash.domain.model.CollectionItem

class FeedCollectionFragment : BaseFragment1WithViewBinding<FragmentFeedCollectionBinding>(
   inflateViewBinding = FragmentFeedCollectionBinding::inflate,
){
    private val vm by viewModels<feedcollectionViewModel>(
        factoryProducer = {
            viewModelFactory {
                addInitializer(feedcollectionViewModel::class){
                    feedcollectionViewModel(unsplashApiService = UnsplashServiceLocator.unsplashApiService)
                }
            }
        }
    )

    private val feedCollectionItemAdapter by lazy {
        FeedCollectionItemAdapter(
            onClick = ::onClick,
            requestManager = Glide.with(this@FeedCollectionFragment)
        )
    }

    private fun onClick(collectionsItem: FeedCollectionUiState.CollectionsItem) {
         startActivity(Intent(requireContext(),DemoFragment::class.java))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindViewModel()
    }


    private fun setupView() {
        binding.recyclerView.run {
            setHasFixedSize(true)
            setItemViewCacheSize(40)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = feedCollectionItemAdapter
        }
    }

    private fun bindViewModel() {
        vm.liveData.observe(viewLifecycleOwner,::render)

        val linearLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && linearLayoutManager.findLastVisibleItemPosition() + 3 >= linearLayoutManager.itemCount){
                    vm.loadNextPage()
                }
            }
        })
    }

    private fun render(feedCollectionUiState: FeedCollectionUiState?) {
       when(feedCollectionUiState){
           FeedCollectionUiState.FirstPageLoading ->{
               binding.run {
                   textError.isGone = true
                   progressBar.isVisible= true
               }
               feedCollectionItemAdapter.submitList(emptyList())
           }
           FeedCollectionUiState.FirstPageError ->{
               binding.run {
                   textError.isVisible =true
                   progressBar.isGone =true
                   textError.text ="Đã có lỗi , hãy kiểm tra lại"
               }
               feedCollectionItemAdapter.submitList(emptyList())
           }
           is FeedCollectionUiState.Content -> {
               binding.run {
                   textError.isGone = true
                   progressBar.isGone = true
               }
               feedCollectionItemAdapter.submitList(feedCollectionUiState.item)
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
    }
}
