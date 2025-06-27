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


class SearchhFragment : BaseFragment1WithViewBinding<FragmentSearchhBinding>(
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
        setupView()
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewpager.run {
            adapter = ViewpagerSearchAdapter(this@SearchhFragment)

            TabLayoutMediator(
                binding.tablayout,
                this
            ){tab, position ->
                tab.text = when(position){
                    0 -> "Photos"
                    1 -> "Users"
                    else -> error("error $position")
                }
            }.attach()
        }
    }

    fun setupView(){
        binding.toolbar.setNavigationOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                vm.setSearchQuery(p0.toString())
            }

        })
    }


}

class ViewpagerSearchAdapter(fragment: Fragment):FragmentStateAdapter(fragment){
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> SearchPhotoFragment.inStance()
           1 -> SearchUserFragment.instance()
           else -> error("error :$position")
       }
    }

}