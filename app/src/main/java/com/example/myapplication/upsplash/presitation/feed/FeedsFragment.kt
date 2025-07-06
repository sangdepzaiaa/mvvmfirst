package com.example.myapplication.upsplash.presitation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.replace
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFeedsBinding
import com.example.myapplication.upsplash.presitation.feed.collection.FeedCollectionFragment
import com.example.myapplication.upsplash.presitation.feed.photo.FeedPhotoFragment
import com.example.myapplication.upsplash.presitation.search.SearchhFragment
import com.google.android.material.tabs.TabLayoutMediator
import androidx.fragment.app.add
import com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener

class FeedsFragment: BaseFragment1WithViewBinding<FragmentFeedsBinding>(
    inflateViewBinding = FragmentFeedsBinding::inflate
){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<SearchhFragment>(
                    containerViewId = R.id.container,
                    tag = SearchhFragment::class.java.name
                )
            }
        }
        setupViewpager()
    }

    private fun setupViewpager() {
        binding.viewPager.run {
            adapter = viewpagerAdapter(this@FeedsFragment)

            TabLayoutMediator(
                binding.tabsLayout,
                this
            ){tab, position ->
                tab.text = when(position){
                    0 -> "Collections"
                    1 -> "Photos"
                    else -> error("error $position")
                }


            }.attach()
        }
    }


}

class viewpagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FeedCollectionFragment.newInstance()
            1 -> FeedPhotoFragment.newInstance()
            else -> error("error $position")
        }
    }

}
