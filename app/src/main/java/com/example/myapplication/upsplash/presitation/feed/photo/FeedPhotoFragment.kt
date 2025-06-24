package com.example.myapplication.upsplash.presitation.feed.photo

import android.os.Bundle
import android.view.View
import com.example.myapplication.Fragments.BaseFragment1WithViewBinding
import com.example.myapplication.databinding.FragmentFeedPhotoBinding


class FeedPhotoFragment : BaseFragment1WithViewBinding<FragmentFeedPhotoBinding>(
    inflateViewBinding = FragmentFeedPhotoBinding::inflate,
){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object{
        fun newInstance() = FeedPhotoFragment()
    }

}